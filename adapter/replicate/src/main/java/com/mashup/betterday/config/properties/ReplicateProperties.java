package com.mashup.betterday.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReplicateProperties {

    @Value("${replicate.api.version}")
    private String version;

    @Value("${replicate.api.key}")
    private String apiKey;

}
