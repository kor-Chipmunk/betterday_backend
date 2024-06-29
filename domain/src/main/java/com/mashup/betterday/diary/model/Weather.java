package com.mashup.betterday.diary.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Weather {

    UNKNOWN,
    SUNNY,
    CLOUDY,
    MOSTLY_CLOUDY,
    LIGHT_RAIN,
    RAIN,
    HEAVY_RAIN,
    LIGHT_SNOW,
    SNOW,
    BLIZZARD,
    THUNDERSTORM,
    WINDY,
    FOGGY,
    HUMID,
    DRY
    ;

    private static final Map<String, Weather> maps = Arrays.stream(Weather.values()).collect(
            Collectors.toUnmodifiableMap(Weather::name, Function.identity())
    );

    public static Weather from(String weather) {
        return maps.getOrDefault(weather, UNKNOWN);
    }

}
