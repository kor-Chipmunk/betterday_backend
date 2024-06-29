package com.mashup.betterday.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class OAuthUser {

    private final String userId;
    private final String email;
    private final String profileImage;

    public static OAuthUser of(
            String userId,
            String email,
            String profileImage
    ) {
        return OAuthUser.builder()
                .userId(userId)
                .email(email)
                .profileImage(profileImage)
                .build();
    }
}
