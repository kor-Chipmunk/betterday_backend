package com.mashup.betterday.notice.model;

import com.mashup.betterday.notice.exception.NoticeValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Content {

    private final String value;

    public Content(String value) {
        validateContent(value);

        this.value = value;
    }

    private void validateContent(String value) {
        if (value == null || value.isEmpty()) {
            throw new NoticeValidationException("내용이 비어있습니다.");
        }
    }

    public static Content of(String value) {
        return new Content(value);
    }

}
