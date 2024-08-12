package com.mashup.betterday.model.diary;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class DiaryCreateRequest {
    private String uid;
    private String content;
    private String weather;
    private ZonedDateTime writtenAt;
}
