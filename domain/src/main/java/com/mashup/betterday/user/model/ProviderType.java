package com.mashup.betterday.user.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProviderType {
    UNKNOWN,
    DEFAULT,
    GOOGLE,
    APPLE,
    KAKAO,
    TWITTER,
    TIKTOK,
    ;

    private static final Map<String, ProviderType> maps = Arrays.stream(ProviderType.values())
            .collect(
                    Collectors.toUnmodifiableMap(ProviderType::name, Function.identity())
            );

    public static ProviderType from(String type) {
        return maps.getOrDefault(type, UNKNOWN);
    }
}
