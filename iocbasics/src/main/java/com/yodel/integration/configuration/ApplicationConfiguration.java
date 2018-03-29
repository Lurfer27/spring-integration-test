package com.yodel.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public String getMessage() {
        return new String("Hello");
    }
}
