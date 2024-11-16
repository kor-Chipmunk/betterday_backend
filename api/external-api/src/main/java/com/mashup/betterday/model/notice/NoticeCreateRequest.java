package com.mashup.betterday.model.notice;

import lombok.Data;

@Data
public class NoticeCreateRequest {
    private String title;
    private String content;
}
