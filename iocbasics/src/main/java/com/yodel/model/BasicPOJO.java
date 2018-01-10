package com.yodel.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BasicPOJO {

    private String name;
    private LoggingColorRandomizer loggingColorRandomizer;

    public BasicPOJO() {
    }

    public String getName() {
        return name;
    }

    @Autowired(required = true)
    public void setName(
        @Value("defaultName") String name
    ) {
        this.name = name;
    }

    public LoggingColorRandomizer getLoggingColorRandomizer() {
        return loggingColorRandomizer;
    }

    @Autowired(required = true)
    public void setLoggingColorRandomizer(
        @Qualifier(value = "loggingColorRandomizer") LoggingColorRandomizer loggingColorRandomizer
    ) {
        this.loggingColorRandomizer = loggingColorRandomizer;
    }
}
