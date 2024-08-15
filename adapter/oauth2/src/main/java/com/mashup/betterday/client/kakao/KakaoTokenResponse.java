package com.mashup.betterday.client.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoTokenResponse {

    @JsonProperty("id_token")
    private String idToken;// eyJraWQiOiI5ZjI1...4MHfgFoTZAZ3TNjMdxOtHH_zCr1w

    private String scope; // openid

    @JsonProperty("access_token")
    private String accessToken; // 2vFv4lvlo....QfpCLbGP5Eb7W-4

    @JsonProperty("refresh_token")
    private String refreshToken; // zBfGmv9ChD4...RVQfpBbbGP5Eb7W-4

    @JsonProperty("expires_in")
    private Long expiresIn; // 21599, 6h

    @JsonProperty("refresh_token_expires_in")
    private Long refreshTokenExpiresIn; // 5183999, 60d

    @JsonProperty("token_type")
    private String tokenType; // bearer


}
