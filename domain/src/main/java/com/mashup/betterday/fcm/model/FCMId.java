package com.mashup.betterday.fcm.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class FCMId {

    private Long value;

    public static FCMId empty() {
        return new FCMId(0L);
    }

}
