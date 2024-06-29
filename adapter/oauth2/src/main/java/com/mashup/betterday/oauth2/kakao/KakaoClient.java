package com.mashup.betterday.oauth2.kakao;

import com.mashup.betterday.model.OAuthToken;
import com.mashup.betterday.model.OAuthUser;
import com.mashup.betterday.oauth2.OAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class KakaoClient implements OAuthClient {
    private final WebClient webClient;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Override
    public OAuthToken requestAccessToken(String code) {
        return webClient.post()
                .uri(tokenUri)
                .body(BodyInserters.fromFormData("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("code", code)
                        .with("grant_type", "authorization_code")
                        .with("redirect_uri", redirectUri)
                )
                .retrieve()
                .bodyToMono(AppleAccessToken.class)
                .map(token -> OAuthToken.of(
                                token.getAccess_token(),
                                token.getRefresh_token(),
                                token.getToken_type()
                        )
                )
                .block();
    }

    @Override
    public OAuthToken renewAccessToken(OAuthToken oAuthToken) {
        return webClient.post()
                .uri(tokenUri)
                .body(BodyInserters.fromFormData("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("grant_type", "refresh_token")
                        .with("refresh_token", oAuthToken.getOAuthRefreshToken())
                )
                .retrieve()
                .bodyToMono(AppleAccessToken.class)
                .map(token -> OAuthToken.of(
                                token.getAccess_token(),
                                token.getRefresh_token(),
                                token.getToken_type()
                        )
                )
                .block();
    }

    @Override
    public OAuthUser requestUserInfo(OAuthToken oAuthToken) {
        return webClient.get()
                .uri(userInfoUri)
                .headers(headers -> headers.setBearerAuth(oAuthToken.getOAuthAccessToken()))
                .retrieve()
                .bodyToMono(KakaoUser.class)
                .map(kakaoUser -> OAuthUser.of(
                                kakaoUser.getId(),
                                kakaoUser.getKakao_account().getEmail(),
                                kakaoUser.getKakao_account().getProfile()
                        )
                )
                .block();
    }
}
