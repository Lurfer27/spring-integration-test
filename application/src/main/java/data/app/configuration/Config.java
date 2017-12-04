package data.app.configuration;

import data.app.endpoints.*;
import data.app.model.FromData;
import data.app.model.ToData;
import data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.support.GenericMessage;

import java.util.NoSuchElementException;

@Configuration
public class Config {

    @Autowired
    private Service externalSource;

    @Autowired
    private ToDataServiceActivator toDataServiceActivator;

    @Bean
    public MessageSource<Integer> inboundChannelAdapter() {
        MessageSource<Integer> inboundChannelAdapter = () -> {
            try {
                int value = externalSource.getNextValue();
                return new GenericMessage<>(value);
            } catch (NoSuchElementException e) {
                return null;
            }
        };
        return inboundChannelAdapter;
    }

    @Bean
    public DirectChannel fromChannel() {
        DirectChannel channel = new DirectChannel();
        channel.setFailover(false);
        channel.setMaxSubscribers(1);
        channel.setDatatypes(FromData.class);
        return channel;
    }

    @Bean
    public FromDataTransformer fromDataTransformer() {
        return new FromDataTransformer();
    }

    @Bean
    public ToDataCorrelationStrategy toDataCorrelationStrategy() {
        return new ToDataCorrelationStrategy();
    }

    @Bean
    public ToDataReleaseStrategy toDataReleaseStrategy() {
        ToDataReleaseStrategy x = new ToDataReleaseStrategy();
        return new ToDataReleaseStrategy();
    }

    @Bean
    public FromDataSplitter fromDataSplitter() {
        return new FromDataSplitter();
    }

    @Bean
    public ToDataOutputRouter toDataOutputRouter() {
        return new ToDataOutputRouter();
    }

    @Bean
    public IntegrationFlow flow() {
        return IntegrationFlows
            .from(fromChannel())
            .log(LoggingHandler.Level.DEBUG, "FromChannel.HEADERS", message -> message.getHeaders())
            .log(LoggingHandler.Level.DEBUG, "FromChannel.PAYLOAD", message -> message.getPayload())
            .filter(FromDataFilter::isBetweenOneAndTen)
            .transform(fromDataTransformer())
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Transform.HEADERS", message -> message.getHeaders())
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Transform.PAYLOAD", message -> message.getPayload())
            .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.headerExpression("type", "payload.getType()"))
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Enrich.HEADERS", message -> message.getHeaders())
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Enrich.PAYLOAD", message -> message.getPayload())
            .aggregate(
                aggregatorSpec -> aggregatorSpec
                    .correlationStrategy(toDataCorrelationStrategy())
                    .releaseStrategy(toDataReleaseStrategy())
            )
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Aggregate.HEADERS", message -> message.getHeaders())
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Aggregate.PAYLOAD", message -> message.getPayload())
            .split()
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Split.HEADERS", message -> message.getHeaders())
            .log(LoggingHandler.Level.DEBUG, "FromChannel-Split.PAYLOAD", message -> message.getPayload())
            .handle((payload, headers) -> this.toDataServiceActivator.setToOk((ToData)payload))
            .handle(toDataOutputRouter())
            .get();
    }
}
