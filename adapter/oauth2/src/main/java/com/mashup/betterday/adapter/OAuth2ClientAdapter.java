package com.mashup.betterday.adapter;

import com.mashup.betterday.client.OAuth2Client;
import com.mashup.betterday.client.google.GoogleClient;
import com.mashup.betterday.client.kakao.KakaoClient;
import com.mashup.betterday.user.model.ProviderType;
import com.mashup.port.OAuth2ClientPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2ClientAdapter implements OAuth2ClientPort {

    private final GoogleClient googleClient;
    private final KakaoClient kakaoClient;

    private List<OAuth2Client> getOAuth2Clients() {
        return List.of(
                googleClient,
                kakaoClient
        );
    }

    private OAuth2Client findOAuth2Client(ProviderType providerType) {
        return getOAuth2Clients().stream()
                .filter(client -> client.supports(providerType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported provider type"));
    }

    @Override
    public TokenResponse getToken(TokenRequest request) {
        OAuth2Client oAuth2Client = findOAuth2Client(request.getProviderType());

        OAuth2Client.TokenResponse tokenResponse = oAuth2Client.getToken(
                new OAuth2Client.TokenRequest(request.getGrantCode()));

        return new TokenResponse(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
    }

    @Override
    public ProfileResponse getProfile(ProfileRequest request) {
        OAuth2Client oAuth2Client = findOAuth2Client(request.getProviderType());
        OAuth2Client.ProfileResponse profileResponse = oAuth2Client.getProfile(
                new OAuth2Client.ProfileRequest(request.getAccessToken()));
        return new ProfileResponse(
                profileResponse.getId(),
                profileResponse.getEmail()
        );
    }

    @Override
    public AuthorizeResponse getAuthorizeURL(AuthorizeRequest request) {
        OAuth2Client oAuth2Client = findOAuth2Client(request.getProviderType());
        return new AuthorizeResponse(oAuth2Client.getAuthorizeURL());
    }
}
