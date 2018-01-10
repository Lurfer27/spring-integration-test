package com.yodel.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class LoggingColorRandomizer extends ColorRandomizer {

    private File logFile;
    private FileWriter writer;

    @Autowired(required = true)
    public void setLogFile(
        @Qualifier(value = "logFile") File logFile
    ) {
        this.logFile = logFile;
    }

    void writeFileLine(String fileLine) {
        try {
            writer.write(fileLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void setUp() throws IOException {
        final boolean append = true;
        writer = new FileWriter(this.logFile, append);
        writeFileLine("loggingColorRandomizer.setUp()");
    }

    @PreDestroy
    public void tearDown() throws IOException {
        writeFileLine("loggingColorRandomizer.tearDown()");
        writer.flush();
        writer.close();
    }

    @Override
    public ColorEnum randomColor() {
        ColorEnum col = super.randomColor();
        writeFileLine("RandomColor:" + col);
        return col;
    }

    @Override
    public ColorEnum exceptColor(ColorEnum ex) {
        ColorEnum col =  super.exceptColor(ex);
        writeFileLine("ExceptColor:" + col);
        return col;
    }
}
