package com.mashup.betterday.config.fcm;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class AsyncProperties {
    private int coreSize;
    private int queueCapacity;
    private int maxSize;
    private Duration keepAlive;
}
