package data.app;

import data.app.stream.MySupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import data.service.Service;
import data.service.ServiceConfiguration;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.NoSuchElementException;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

@SpringBootApplication
@Import(value = ServiceConfiguration.class)
public class Application implements CommandLineRunner {

    private final Service service;

    @Autowired
    private DirectChannel fromChannel;

    @Autowired
    public Application(Service service) {
        this.service = service;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
            .web(false)
            .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        MySupplier supplier = new MySupplier(service);
        try {
            while (true) {
                int value = IntStream.generate(supplier).iterator().nextInt();
                //int value = service.getNextValue();
                Message<?> message = new GenericMessage<>(value);
                fromChannel.send(message);
            }
        } catch (NoSuchElementException e) {
            // Do Nothing
        }
    }
}