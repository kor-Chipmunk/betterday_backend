package com.mashup.betterday.model.diary;

import lombok.Data;

@Data
public class DiaryCreateRequest {
    private String uid;
    private String content;
    private String weather;
}
