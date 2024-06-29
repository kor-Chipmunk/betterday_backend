package com.mashup.betterday.oauth2.google;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GoogleUser {
    private final String email;
    private final String verified_email;
    private final String sub;
    private final String name;
    private final String given_name;
    private final String family_name;
    private final String picture;
}
