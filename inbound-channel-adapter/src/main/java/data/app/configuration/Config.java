package data.app.configuration;

import data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
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
}
