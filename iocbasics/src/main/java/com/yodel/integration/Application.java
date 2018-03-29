package com.yodel.integration;

import com.yodel.integration.services.CsvReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.StringReader;
import java.util.List;

@SpringBootApplication
@ImportResource(locations = { "classpath:context.xml" })
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
        CsvReader csvReader = ctx.getBean("csvGateway", CsvReader.class);

        for (String s : args) {
            System.out.println(s);
            StringReader stringReader = new StringReader(s);

            List<?> list = csvReader.toList(stringReader);

            for (Object o : list) {
                System.out.println(o.toString());
            }
        }

        String myString = ctx.getBean("getMessage", String.class);
        System.out.println(myString);
    }
}
