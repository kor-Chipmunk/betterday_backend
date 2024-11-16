package com.mashup.betterday.notice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class NoticeId {

    private Long value;

    public static NoticeId empty() {
        return new NoticeId(0L);
    }

}
