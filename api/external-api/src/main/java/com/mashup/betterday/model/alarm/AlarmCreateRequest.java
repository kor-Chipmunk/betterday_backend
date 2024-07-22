package com.mashup.betterday.model.alarm;

import lombok.Data;

@Data
public class AlarmCreateRequest {
    private String diaryUid;
    private String link;
}
