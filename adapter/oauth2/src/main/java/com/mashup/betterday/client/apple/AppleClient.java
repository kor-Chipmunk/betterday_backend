package com.mashup.betterday.client.apple;

import com.mashup.betterday.client.OAuth2Client;
import com.mashup.betterday.client.apple.model.JWTSet;
import com.mashup.betterday.config.properties.AppleProviderProperties;
import com.mashup.betterday.config.properties.AppleRegistrationProperties;
import com.mashup.betterday.user.model.ProviderType;
import io.jsonwebtoken.Claims;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppleClient implements OAuth2Client {

    private final AppleRegistrationProperties registrationProperties;
    private final AppleProviderProperties providerProperties;

    private final RestTemplate restTemplate;

    private final AppleJwtParser appleJwtParser;

    private final AppleJwtProvider appleJwtProvider;

    private final ApplePublicKeyGenerator applePublicKeyGenerator;

    private final P8KeyLoader p8KeyLoader;

    @Override
    public boolean supports(ProviderType providerType) {
        return providerType == ProviderType.APPLE;
    }

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        PrivateKey privateKey = p8KeyLoader.loadPrivateKey(registrationProperties.getKeyPath());

        AppleTokenRequest appleTokenRequest = new AppleTokenRequest(
                tokenRequest.getGrantCode(),
                registrationProperties.getRedirectUri(),
                registrationProperties.getClientId(),
                appleJwtProvider.createClientSecret(
                        registrationProperties.getTeamId(),
                        registrationProperties.getClientId(),
                        registrationProperties.getKeyId(),
                        privateKey
                ),
                registrationProperties.getScope(),
                registrationProperties.getAuthorizationGrantType()
        );

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
                appleTokenRequest.toEncodedForm(),
                headers
        );

        ResponseEntity<AppleTokenResponse> response = restTemplate.exchange(
                providerProperties.getTokenUri(),
                HttpMethod.POST,
                requestEntity,
                AppleTokenResponse.class
        );

        return new TokenResponse(response.getBody().getIdToken(), response.getBody().getRefreshToken());
    }

    private JWTSet getApplePublicKey() {
        ResponseEntity<JWTSet> response = restTemplate.exchange(
                providerProperties.getPublicKeyUri(),
                HttpMethod.GET,
                null,
                JWTSet.class
        );
        return response.getBody();
    }

    @Override
    public ProfileResponse getProfile(ProfileRequest profileRequest) {
        Map<String, String> tokenHeaders = appleJwtParser.parseHeaders(profileRequest.getAccessToken());
        JWTSet jwtSet = getApplePublicKey();
        PublicKey publicKey = applePublicKeyGenerator.generatePublicKey(tokenHeaders, jwtSet);
        Claims claims = appleJwtProvider.parseClaims(profileRequest.getAccessToken(), publicKey);

        return new ProfileResponse(
                claims.get("sub", String.class),
                claims.get("email", String.class)
        );
    }

    @Override
    public String getAuthorizeURL() {
        return UriComponentsBuilder.fromHttpUrl(providerProperties.getAuthorizationUri())
                .queryParam("client_id", registrationProperties.getClientId())
                .queryParam("redirect_uri", registrationProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("response_mode", "form_post")
                .queryParam("scope", String.join(" ", registrationProperties.getScope()))
                .toUriString();
    }
}
