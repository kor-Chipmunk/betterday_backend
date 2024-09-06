package com.mashup.betterday.client.apple;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@RequiredArgsConstructor
public class AppleTokenRequest {

    private final String code;

    @JsonProperty("redirect_uri")
    private final String redirectUri;

    @JsonProperty("client_id")
    private final String clientId;

    @JsonProperty("client_secret")
    private final String clientSecret;

    private final List<String> scopes;

    @JsonProperty("grant_type")
    private final String grantType;

    public MultiValueMap<String, String> toEncodedForm() {
        MultiValueMap<String, String> encodedForm = new LinkedMultiValueMap<>();
        encodedForm.add("code", code);
        encodedForm.add("redirect_uri", redirectUri);
        encodedForm.add("client_id", clientId);
        encodedForm.add("client_secret", clientSecret);
        scopes.forEach(scope -> encodedForm.add("scope", scope));
        encodedForm.add("grant_type", grantType);
        return encodedForm;
    }

}
