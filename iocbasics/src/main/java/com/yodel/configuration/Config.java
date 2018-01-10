package com.yodel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class Config {

    @Bean(name = "logFile")
    public File logFile() {
        return new File("colorLog.txt");
    }
}
