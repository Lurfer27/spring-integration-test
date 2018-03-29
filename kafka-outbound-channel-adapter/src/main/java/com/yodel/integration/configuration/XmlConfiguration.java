package com.yodel.integration.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:context.xml"})
public class XmlConfiguration {
}
