package com.mashup.feelring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-aes, application-fcm, application-jwt, application-oauth2, application-postgres, application-security");
        SpringApplication.run(ExternalApiApplication.class, args);
    }

}
