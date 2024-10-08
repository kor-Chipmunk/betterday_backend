package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import java.time.ZonedDateTime;
import lombok.Data;

public interface DiaryCreateUsecase {
    Diary create(Request request);

    @Data
    class Request {
        private final String uid;
        private final String content;
        private final String weather;
        private final ZonedDateTime writtenAt;
        private final User user;
    }
}
