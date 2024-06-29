package com.mashup.betterday.diary.exception;

public class DiaryValidationException extends RuntimeException {
    public DiaryValidationException() {
        super();
    }

    public DiaryValidationException(String message) {
        super(message);
    }

    public DiaryValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
