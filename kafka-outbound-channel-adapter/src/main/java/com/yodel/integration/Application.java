package com.yodel.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootApplication
public class Application {

    @Value(value = "classpath:request.json")
    private Resource requestJson;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).web(false).run(args);
        context.getBean(Application.class).doIt(context);
    }

    private void doIt(ConfigurableApplicationContext context) throws IOException {
        String request = StreamUtils.copyToString(this.requestJson.getInputStream(), Charset.defaultCharset());

        MessageChannel channel = context.getBean("inputToKafka", MessageChannel.class);

        for (int i=1; i <2; i++) {
            Message<String> message = MessageBuilder
                    .withPayload(request)
                    .setHeader("kafka_messageKey", "key-" + i)
                    .build();
            channel.send(message);
        }
    }
}
