package data.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import data.service.ServiceConfiguration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;

@SpringBootApplication
@Import(value = ServiceConfiguration.class)
public class Application implements CommandLineRunner {

    @Autowired
    private MessageSource<Integer> messageSource;

    @Autowired
    private DirectChannel fromChannel;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
            .web(false)
            .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Message<Integer> message = messageSource.receive();
            if (message == null) {
                break;
            }
            fromChannel.send(message);
        }
    }
}