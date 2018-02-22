package com.yodel.integration.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public abstract class StringCsvReader implements CsvReader<StringReader> {

    private static final char Comma = ',';

    private static boolean IsDelimiter(char inChar) {
        if (inChar == Comma) {
            return true;
        }
        return false;
    }

    protected abstract StringBuilder createStringBuilder();

    @Override
    public List<?> toList(StringReader csvRecord) {
        List<String> list = new ArrayList<>();

        try {
            StringBuilder stringBuilder = this.createStringBuilder();
            int charAsInt = csvRecord.read();
            while (charAsInt != -1) {
                char c = (char) charAsInt;
                if (IsDelimiter(c)) {
                    list.add(stringBuilder.toString());
                    stringBuilder = this.createStringBuilder();
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
