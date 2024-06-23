package com.mashup.feelring.config.fcm;

import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@RequiredArgsConstructor
@EnableConfigurationProperties(AsyncProperties.class)
@Configuration
public class AsyncConfig {

    private final AsyncProperties asyncProperties;

    @Bean
    public Executor fcmExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(asyncProperties.getCoreSize());
        executor.setQueueCapacity(asyncProperties.getQueueCapacity());
        executor.setMaxPoolSize(asyncProperties.getMaxSize());
        executor.setKeepAliveSeconds((int) asyncProperties.getKeepAlive().getSeconds());
        executor.setThreadNamePrefix("fcm-");

        executor.initialize();
        return executor;
    }

}
