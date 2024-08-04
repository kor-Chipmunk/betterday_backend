package com.mashup.betterday.model.diary;

import lombok.Data;

@Data
public class DiaryUpdateRequest {
    private String content;
    private String weather;
}
