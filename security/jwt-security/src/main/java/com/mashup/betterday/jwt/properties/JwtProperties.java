package com.mashup.betterday.jwt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String issuer;

    private TokenProperty accessToken;

    private TokenProperty refreshToken;

    @Data
    public static class TokenProperty {
        private String secretKey;
        private Long expiration;
    }

}
