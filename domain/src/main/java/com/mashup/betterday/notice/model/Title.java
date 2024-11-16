package com.mashup.betterday.notice.model;

import com.mashup.betterday.notice.exception.NoticeValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Title {

    private final String value;

    public Title(String value) {
        validateTitle(value);

        this.value = value;
    }

    private void validateTitle(String value) {
        if (value == null || value.isEmpty()) {
            throw new NoticeValidationException("제목이 비어있습니다.");
        }
    }

    public static Title of(String value) {
        return new Title(value);
    }

}
