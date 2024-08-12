package com.mashup.feelring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.mashup.feelring")
@EnableAutoConfiguration
@Configuration
public class DataJpaConfig {
}
