package com.mashup.betterday.model.diary;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Data;

@Data
public class DiarySyncBatchRequest {
    private List<BatchDiaryItem> diaries;

    @Data
    public static class BatchDiaryItem {
        private String uid;
        private String content;
        private String weather;
        private ZonedDateTime writtenAt;
        private LocalDateTime createdAt;
    }
}
