package com.mashup.betterday.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.kakao")
public class KakaoProviderProperties {

    private String authorizationUri;
    private String responseType;
    private String accessType;

    private String tokenUri;

    private String userInfoUri;

    private String userNameAttribute;

}
