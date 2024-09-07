package com.mashup.betterday.config;

import com.mashup.betterday.config.properties.ReplicateProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReplicateAuthorizationInterceptor implements RequestInterceptor {

    private final ReplicateProperties replicateProperties;

    @Override
    public void apply(feign.RequestTemplate template) {
        template.header("Authorization", "Bearer " + replicateProperties.getApiKey());
    }

}
