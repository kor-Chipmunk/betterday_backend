package com.mashup.betterday.diary.model;

import com.mashup.betterday.diary.exception.ContentValidationException;
import com.mashup.betterday.diary.exception.DiaryValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Content {
    public static final int MAX_CONTENT_LENGTH = 280;

    private String content;

    public Content(String content) {
        validateContentSize(content);

        this.content = content;
    }

    void validateContentSize(String content) throws DiaryValidationException {
        if (content == null) {
            throw new ContentValidationException("내용은 필수 값입니다.");
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new ContentValidationException("일기 내용이 최대 길이를 초과했습니다.");
        }
    }

}
