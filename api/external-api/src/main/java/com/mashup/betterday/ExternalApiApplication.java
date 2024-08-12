package com.mashup.betterday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name",
                "application, application-aes, application-aws-sns, application-jasypt, application-fcm, application-jwt, application-oauth2, application-postgres, application-security, application-spring-event");
        SpringApplication.run(ExternalApiApplication.class, args);
    }

}
