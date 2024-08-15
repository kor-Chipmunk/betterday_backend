package com.mashup.betterday.client.kakao;

import com.mashup.betterday.client.OAuth2Client;
import com.mashup.betterday.config.properties.KakaoProviderProperties;
import com.mashup.betterday.config.properties.KakaoRegistrationProperties;
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
public class KakaoClient implements OAuth2Client {

    private final KakaoRegistrationProperties registrationProperties;
    private final KakaoProviderProperties providerProperties;

    private final RestTemplate restTemplate;

    @Override
    public boolean supports(ProviderType providerType) {
        return providerType == ProviderType.KAKAO;
    }

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        KakaoTokenRequest kakaoTokenRequest = new KakaoTokenRequest(
                tokenRequest.getGrantCode(),
                registrationProperties.getRedirectUri(),
                registrationProperties.getClientId(),
                registrationProperties.getClientSecret(),
                registrationProperties.getScope(),
                registrationProperties.getAuthorizationGrantType()
        );

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
                kakaoTokenRequest.toEncodedForm(),
                headers
        );

        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                providerProperties.getTokenUri(),
                HttpMethod.POST,
                requestEntity,
                KakaoTokenResponse.class
        );

        log.info(response.toString());

        return new TokenResponse(response.getBody().getAccessToken(), response.getBody().getRefreshToken());
    }

    @Override
    public ProfileResponse getProfile(ProfileRequest profileRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(profileRequest.getAccessToken());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        log.info("url: {}", providerProperties.getUserInfoUri());

        ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                providerProperties.getUserInfoUri(),
                HttpMethod.GET,
                requestEntity,
                KakaoUserResponse.class
        );
    
        log.info("response: {}", response.getBody().toString());

        return new ProfileResponse(
                response.getBody().getId(),
                response.getBody().getId() + "@kakao.com" // 카카오 사업자 신청 대신 임시로 이메일을 만들어서 사용
        );
    }

    @Override
    public String getAuthorizeURL() {
        return UriComponentsBuilder.fromHttpUrl(providerProperties.getAuthorizationUri())
                .queryParam("client_id", registrationProperties.getClientId())
                .queryParam("redirect_uri", registrationProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("scope", String.join("+", registrationProperties.getScope()))
                .toUriString();
    }
}
