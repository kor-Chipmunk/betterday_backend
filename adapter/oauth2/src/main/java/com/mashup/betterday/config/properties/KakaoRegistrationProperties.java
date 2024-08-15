package com.mashup.betterday.config.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public class KakaoRegistrationProperties {

    private String clientId;
    private String clientSecret;

    private String authorizationGrantType;
    private String redirectUri;
    private List<String> scope;

    private String clientAuthenticationMethod;
    private String clientName;

}
