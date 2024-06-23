package com.mashup.feelring;

import com.mashup.feelring.diary.model.Diary;
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
