package com.yodel.model;

public class BasicPOJO {

    private String name;
    private LoggingColorRandomizer loggingColorRandomizer;

    public BasicPOJO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoggingColorRandomizer getLoggingColorRandomizer() {
        return loggingColorRandomizer;
    }

    public void setLoggingColorRandomizer(LoggingColorRandomizer loggingColorRandomizer) {
        this.loggingColorRandomizer = loggingColorRandomizer;
    }
}
