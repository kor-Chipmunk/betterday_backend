package com.mashup.betterday.client.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleTokenResponse {

    @JsonProperty("id_token")
    private String idToken;// eyJhbGciOi...kW8svQcAz-mgQcMbrQ

    private String scope; // https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile openid

    @JsonProperty("access_token")
    private String accessToken; // ya29.a0AcM612ydzUA...M7Vqw23k3wpT6JkZpbxCKg0171

    @JsonProperty("refresh_token")
    private String refreshToken; // 1//0ejycm9f5inR_Cg...iESQrl55DZvhf5wDSRz7bUCNKoI

    @JsonProperty("expires_in")
    private Long expiresIn; // 3599

    @JsonProperty("token_type")
    private String tokenType; // Bearer

}
