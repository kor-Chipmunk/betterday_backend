package com.mashup.betterday.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "client")
public class ClientProperties {

    private Long readTimeout;
    private Long writeTimeout;

}
