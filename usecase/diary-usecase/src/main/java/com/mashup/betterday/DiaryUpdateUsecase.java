package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import lombok.Data;

public interface DiaryUpdateUsecase {
    Diary update(Request request);

    @Data
    class Request {
        private final String uid;
        private final String content;
        private final String weather;
    }
}
