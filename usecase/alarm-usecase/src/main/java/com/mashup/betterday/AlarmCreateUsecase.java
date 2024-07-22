package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import lombok.Data;

public interface AlarmCreateUsecase {
    Alarm create(Request request);

    @Data
    class Request {
        private final Long diaryId;
        private final String link;
    }
}
