package com.yodel.integration.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StringCsvReader implements CsvReader<StringReader> {

    private static final char comma = ',';

    private boolean isDelimiter(char inChar) {
        if (inChar == comma) {
            return true;
        }
        return false;
    }

    @Override
    public List<?> toList(StringReader csvRecord) {

        List<String> list = new ArrayList<>();

        try {
            StringBuilder stringBuilder = new StringBuilder();
            int charAsInt = csvRecord.read();
            while (charAsInt != -1) {
                char c = (char) charAsInt;
                if (isDelimiter(c)) {
                    list.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                } else {
                    stringBuilder.append(c);
                }
                charAsInt = csvRecord.read();
            }
            list.add(stringBuilder.toString());
            csvRecord.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
