package com.yodel.integration;

import com.yodel.integration.services.CsvReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CsvReader csvReader = context.getBean("csvGateway", CsvReader.class);

        for (String s : args) {
            System.out.println(s);

            List<?> list = csvReader.toList(s);

            for (Object o : list) {
                System.out.println(o.toString());
            }
        }
    }
}
