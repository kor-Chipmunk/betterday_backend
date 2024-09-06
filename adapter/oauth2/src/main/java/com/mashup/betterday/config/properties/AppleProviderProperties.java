package com.mashup.betterday.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.apple")
public class AppleProviderProperties {

    private String issuerUri;
    private String tokenUri;
    private String publicKeyUri;
    private String authorizationUri;
    private String userNameAttribute;

}
