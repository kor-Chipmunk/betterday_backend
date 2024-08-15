package com.mashup.betterday.client.google;

import com.mashup.betterday.client.OAuth2Client;
import com.mashup.betterday.config.properties.GoogleProviderProperties;
import com.mashup.betterday.config.properties.GoogleRegistrationProperties;
import com.mashup.betterday.user.model.ProviderType;
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
public class GoogleClient implements OAuth2Client {

    private final GoogleRegistrationProperties registrationProperties;
    private final GoogleProviderProperties providerProperties;

    private final RestTemplate restTemplate;

    @Override
    public boolean supports(ProviderType providerType) {
        return providerType == ProviderType.GOOGLE;
    }

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        GoogleTokenRequest googleTokenRequest = new GoogleTokenRequest(
                tokenRequest.getGrantCode(),
                registrationProperties.getRedirectUri(),
                registrationProperties.getClientId(),
                registrationProperties.getClientSecret(),
                registrationProperties.getScope(),
                registrationProperties.getAuthorizationGrantType()
        );

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
                googleTokenRequest.toEncodedForm(),
                headers
        );

        ResponseEntity<GoogleTokenResponse> response = restTemplate.exchange(
                providerProperties.getTokenUri(),
                HttpMethod.POST,
                requestEntity,
                GoogleTokenResponse.class
        );

        return new TokenResponse(response.getBody().getAccessToken(), response.getBody().getRefreshToken());
    }

    @Override
    public ProfileResponse getProfile(ProfileRequest profileRequest) {
        String url = UriComponentsBuilder.fromHttpUrl(providerProperties.getUserInfoUri())
                .queryParam("access_token", profileRequest.getAccessToken())
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                GoogleUserResponse.class
        );

        return new ProfileResponse(
                response.getBody().getId(),
                response.getBody().getEmail()
        );
    }

    @Override
    public String getAuthorizeURL() {
        return UriComponentsBuilder.fromHttpUrl(providerProperties.getAuthorizationUri())
                .queryParam("client_id", registrationProperties.getClientId())
                .queryParam("redirect_uri", registrationProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("scope", String.join("+", registrationProperties.getScope()))
                .queryParam("access_type", "offline")
                .toUriString();
    }
}
