package com.mashup.betterday.notice.exception;

public class NoticeValidationException extends RuntimeException {
    public NoticeValidationException() {
        super();
    }

    public NoticeValidationException(String message) {
        super(message);
    }

    public NoticeValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
