package com.mashup.betterday.alarm.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class AlarmId {

    private Long value;

    public static AlarmId empty() {
        return new AlarmId(0L);
    }

}
