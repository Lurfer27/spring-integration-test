package com.yodel.integration.services;

public interface CsvService<T> {

    String[] convertToStringArray(T csvRecord);
}
