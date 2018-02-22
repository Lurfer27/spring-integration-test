package com.yodel.integration;

import com.yodel.integration.services.CsvReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.StringReader;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CsvReader csvReader = context.getBean("csvGateway", CsvReader.class);

        for (String s : args) {
            System.out.println(s);
            StringReader stringReader = (StringReader) context.getBean("stringReaderBean", s);

            List<?> list = csvReader.toList(stringReader);

            for (Object o : list) {
                System.out.println(o.toString());
            }
        }
    }
}
