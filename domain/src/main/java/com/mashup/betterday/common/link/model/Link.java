package com.mashup.betterday.common.link.model;

import com.mashup.betterday.common.link.exception.LinkValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Link {
    private final String link;

    public Link(String link) {
        validateLink(link);

        this.link = link;
    }

    private void validateLink(String link) {
        try {
            new java.net.URL(link).toURI();
        } catch (Exception exception) {
            throw new LinkValidationException("URL 검증에 실패했습니다.", exception);
        }
    }

}
