package com.mashup.betterday;

import com.mashup.port.OAuth2ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2TokenService implements OAuth2TokenUsecase {

    private final OAuth2ClientPort oAuth2Port;

    @Override
    public TokenResponse getToken(Request request) {
        OAuth2ClientPort.Response response = oAuth2Port.getToken(
                new OAuth2ClientPort.Request(request.getProviderType(), request.getGrantCode()));

        return new TokenResponse(response.getAccessToken(), response.getRefreshToken());
    }

    @Override
    public ProfileResponse getProfile(String accessToken) {
        OAuth2ClientPort.Profile profile = oAuth2Port.getProfile(accessToken);

        return new ProfileResponse(profile.getId(), profile.getEmail());
    }
}
