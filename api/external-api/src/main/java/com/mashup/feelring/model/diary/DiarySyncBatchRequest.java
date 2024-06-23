package com.mashup.feelring.model.diary;

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
    }
}
