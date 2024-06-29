package com.mashup.betterday.user.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    UNKNOWN,
    ADMIN,
    USER,
    DELETED,
    ;

    private static final Map<String, Role> maps = Arrays.stream(Role.values()).collect(
            Collectors.toUnmodifiableMap(Role::name, Function.identity())
    );

    public static Role from(String role) {
        return maps.getOrDefault(role, UNKNOWN);
    }

}
