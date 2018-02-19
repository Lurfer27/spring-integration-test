package com.yodel.integration.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StringCsvReader implements CsvReader<String>, BeanFactoryAware {

    private static final char Comma = ',';

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    private static boolean IsDelimiter(char inChar) {
        if (inChar == Comma) {
            return true;
        }
        return false;
    }

    @Override
    public List<?> toList(String csvRecord) {
        Reader reader = (StringReader) this.beanFactory.getBean("stringReaderBean", csvRecord);
        List<String> list = new ArrayList<>();

        try {
            StringBuilder stringBuilder = this.beanFactory.getBean("stringBuilderBean", StringBuilder.class);
            int charAsInt = reader.read();
            while (charAsInt != -1) {
                char c = (char) charAsInt;
                if (IsDelimiter(c)) {
                    list.add(stringBuilder.toString());
                    stringBuilder = this.beanFactory.getBean("stringBuilderBean", StringBuilder.class);
                } else {
                    stringBuilder.append(c);
                }
                charAsInt = reader.read();
            }
            list.add(stringBuilder.toString());
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
