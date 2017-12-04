package data.app.configuration;

import data.app.endpoints.OutputRouter;
import data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.support.GenericMessage;

import java.util.NoSuchElementException;

@Configuration
public class Config {

    @Autowired
    private Service externalSource;

    @Bean
    public MessageSource<Integer> inboundChannelAdapter() {
        MessageSource<Integer> retVal = () -> {
            try {
                int value = externalSource.getNextValue();
                return new GenericMessage<>(value);
            } catch (NoSuchElementException e) {
                return null;
            }
        };
        return retVal;
    }

    @Bean
    public DirectChannel fromChannel() {
        DirectChannel channel = new DirectChannel();
        channel.setFailover(false);
        channel.setMaxSubscribers(1);
        channel.setDatatypes(Integer.class);
        return channel;
    }

    @Bean
    public OutputRouter outputRouter() {
        return new OutputRouter();
    }

    @Bean
    public IntegrationFlow flow() {
        return IntegrationFlows
            .from(fromChannel())
            .handle(outputRouter())
            .get();
    }
}
