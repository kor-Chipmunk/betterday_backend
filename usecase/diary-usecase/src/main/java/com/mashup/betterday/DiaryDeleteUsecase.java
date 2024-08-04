package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface DiaryDeleteUsecase {
    Diary delete(Request request);

    @Data
    class Request {
        private final String uid;
        private final User user;
    }
}
