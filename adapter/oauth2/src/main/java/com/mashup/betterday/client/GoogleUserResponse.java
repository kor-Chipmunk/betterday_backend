package com.mashup.betterday.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleUserResponse {

    private String id; // 123456789012345678901

    private String email; // anonymous@gmail.com

    @JsonProperty("verified_email")
    private Boolean verifiedEmail; // true

    private String name; // 다람쥐

    @JsonProperty("given_name")
    private String givenName; // 람쥐

    @JsonProperty("family_name")
    private String familyName; // 다

    private String picture; // https://lh3.googleusercontent.com/a/ACg8ocIY...

}
