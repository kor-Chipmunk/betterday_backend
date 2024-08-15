package com.mashup.betterday.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private Boolean allowedCredentials;

}
