package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import lombok.Data;

public interface AlarmUpdateUsecase {
    Alarm update(Request request);

    @Data
    class Request {
        private final Long alarmId;
        private final String link;
    }
}
