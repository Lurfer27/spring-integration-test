package com.gsam;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationTest {

    @Test
    public void contextLoads() {
        // Given
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
    }
}
