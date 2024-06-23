package com.mashup.feelring.model.diary;

import lombok.Data;

@Data
public class DiaryUpdateRequest {
    private String uid;
    private String content;
    private String weather;
}
