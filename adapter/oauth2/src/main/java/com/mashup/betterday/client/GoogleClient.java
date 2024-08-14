package com.mashup.betterday.client;

import com.mashup.port.OAuth2ClientPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoogleClient implements OAuth2ClientPort {

    private final RestTemplate restTemplate;

    @Override
    public Response getToken(Request request) {
        String url = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        log.info(request.toString());

        GoogleTokenRequest googleTokenRequest = new GoogleTokenRequest(
                request.getGrantCode(),
                "http://localhost:10000/api/v1/auth/oauth2/google",
                "",
                "",
                List.of(
                        "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile",
                        "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email",
                        "openid"
                ),
                "authorization_code"
        );

        log.info(googleTokenRequest.toEncodedForm().toString());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
                googleTokenRequest.toEncodedForm(),
                headers
        );

        ResponseEntity<GoogleTokenResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GoogleTokenResponse.class
        );

        log.info(response.toString());

        return new Response(response.getBody().getAccessToken(), response.getBody().getRefreshToken());
    }

    @Override
    public OAuth2ClientPort.Profile getProfile(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                GoogleUserResponse.class
        );

        return new OAuth2ClientPort.Profile(response.getBody().getId(), response.getBody().getEmail());
    }
}
