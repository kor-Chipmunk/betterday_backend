package com.mashup.betterday.oauth2.apple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AppleUser {
    private final String id;
    private final String profile;
    private final String sub;
    private final String email;
    private final Boolean email_verified;
    private final Boolean is_private_email;
}
