package com.yodel.integration.services;

import java.util.List;

public interface CsvReader<T> {

    List<?> toList(T csvRecord);
}
