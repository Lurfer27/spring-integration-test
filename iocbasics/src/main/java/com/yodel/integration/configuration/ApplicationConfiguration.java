package com.yodel.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class ApplicationConfiguration {

    @Bean
    public String getMessage() {
        return new String("Hello");
    }
}
