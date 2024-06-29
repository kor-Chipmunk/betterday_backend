package com.mashup.betterday;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.mashup.betterday")
@EnableAutoConfiguration
@Configuration
public class DataJpaConfig {
}
