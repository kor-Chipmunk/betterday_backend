package com.mashup.betterday.user.model;

import com.mashup.betterday.user.exception.AdminValidationException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

    public void checkAdmin() {
        if (this != ADMIN) {
            throw new AdminValidationException("관리자 권한이 필요합니다.");
        }
    }
}
