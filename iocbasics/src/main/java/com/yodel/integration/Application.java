package com.yodel.integration;

import com.yodel.integration.services.CsvReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.StringReader;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CsvReader csvReader = context.getBean("csvGateway", CsvReader.class);

        for (String s : args) {
            System.out.println(s);
            StringReader stringReader = new StringReader(s);

            List<?> list = csvReader.toList(stringReader);

            for (Object o : list) {
                System.out.println(o.toString());
            }
        }

        String myString = context.getBean("getMessage", String.class);
        System.out.println(myString);
    }
}
