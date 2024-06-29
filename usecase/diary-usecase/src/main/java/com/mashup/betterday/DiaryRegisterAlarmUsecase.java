package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import lombok.Data;

public interface DiaryRegisterAlarmUsecase {
    Diary registerAlarm(Request request);

    @Data
    class Request {
        private final String uid;
        private final String alarmUrl;
    }
}
