package com.yodel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;

@Configuration
public class Config {

    @Bean(name = "logFile")
    @Scope(value = "singleton")
    public File logFile() {
        return new File("colorLog.txt");
    }
}
