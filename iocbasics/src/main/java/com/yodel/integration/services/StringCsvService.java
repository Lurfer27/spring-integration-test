package com.yodel.integration.services;

public class StringCsvService implements CsvService<String> {

    @Override
    public String[] convertToStringArray(String csvRecord) {
        return new String[] {csvRecord};
    }
}
