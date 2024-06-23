package com.mashup.feelring;

import com.mashup.feelring.diary.model.Diary;
import java.util.List;
import lombok.Data;

public interface DiarySyncBatchUsecase {
    List<Diary> syncBatch(Request request);

    @Data
    class Request {
        private final List<CreateItem> requests;

        @Data
        public static class CreateItem {
            private final String uid;
            private final Long userId;
            private final String content;
            private final String weather;
        }
    }
}
