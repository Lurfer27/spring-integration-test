package com.yodel.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingColorRandomizer extends ColorRandomizer {

    private File logFile;
    private FileWriter writer;

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    void writeFileLine(String fileLine) {
        try {
            writer.write(fileLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        final boolean append = true;
        writer = new FileWriter(this.logFile, append);
        writeFileLine("loggingColorRandomizer.init()");
    }

    public void destroy() throws IOException {
        writeFileLine("loggingColorRandomizer.destroy()");
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
