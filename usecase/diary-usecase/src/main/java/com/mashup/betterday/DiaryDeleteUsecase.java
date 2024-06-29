package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import lombok.Data;

public interface DiaryDeleteUsecase {
    Diary delete(Request request);

    @Data
    class Request {
        private final String uid;
    }
}
