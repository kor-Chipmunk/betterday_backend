package com.mashup.betterday.report.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Feeling {

    UNKNOWN,
    HAPPY,
    EXICTED,
    RELAXED,
    SAD,
    ANXIOUS,
    ANGRY,
    ;

    private static final Map<String, Feeling> maps = Arrays.stream(Feeling.values()).collect(
            Collectors.toUnmodifiableMap(Feeling::name, Function.identity())
    );

    public static Feeling from(String type) {
        return maps.getOrDefault(type, UNKNOWN);
    }

}
