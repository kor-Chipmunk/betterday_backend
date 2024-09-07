package com.mashup.betterday.config;

import com.mashup.betterday.config.decoder.ReplicateErrorDecoder;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients(basePackages = "com.mashup.betterday")
public class OpenFeignConfig {

    private final ReplicateAuthorizationInterceptor replicateAuthorizationInterceptor;

    @Bean
    ErrorDecoder errorDecoder() {
        return new ReplicateErrorDecoder();
    }

    @Bean
    public ReplicateAuthorizationInterceptor feignInterceptor() {
        return replicateAuthorizationInterceptor;
    }


}
