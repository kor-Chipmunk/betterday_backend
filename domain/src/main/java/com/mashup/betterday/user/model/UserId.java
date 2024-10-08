package com.mashup.betterday.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserId {

    private Long value;

    public static UserId empty() {
        return new UserId(0L);
    }

}
