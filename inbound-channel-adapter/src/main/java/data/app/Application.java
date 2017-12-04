package data.app;

import data.service.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;

@SpringBootApplication
@Import(value = ServiceConfiguration.class)
public class Application implements CommandLineRunner {

    @Autowired
    private MessageSource<Integer> messageSource;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .logStartupInfo(false)
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
            System.out.println(message);
        }

    }
}
