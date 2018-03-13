package com.yodel.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).web(false).run(args);
        context.getBean(Application.class).doIt(context);
    }

    private void doIt(ConfigurableApplicationContext context) {
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        MessageChannel channel = context.getBean("inputToKafka", MessageChannel.class);
        Message<String> message = MessageBuilder
                .withPayload("leetest")
                .setHeader("kafka_messageKey", "key-2")
                .build();
        channel.send(message);
    }
}
