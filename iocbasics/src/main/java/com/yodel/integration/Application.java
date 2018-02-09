package com.yodel.integration;

import com.yodel.integration.services.CsvService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CsvService csvService = context.getBean("csvGateway", CsvService.class);

        for (String s : csvService.convertToStringArray("WorldCup")) {
            System.out.println(s);
        }
    }
}
