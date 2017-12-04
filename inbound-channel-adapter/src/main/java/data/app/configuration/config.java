package data.app.configuration;

import data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.NoSuchElementException;

@Configuration
public class config {

    @Autowired
    private Service service;

    @Bean
    public MessageSource<Integer> integerMessageSource() {
        MessageSource<Integer> messageSource = () -> {
            try {
                int value = service.getNextValue();
                return new GenericMessage<>(value);
            } catch (NoSuchElementException e) {
                return null;
            }
        };
        return messageSource;
    }
}
