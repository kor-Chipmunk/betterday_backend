package com.mashup.betterday.report.exception;

public class WeeklyReportValidationException extends RuntimeException {
    public WeeklyReportValidationException() {
        super();
    }

    public WeeklyReportValidationException(String message) {
        super(message);
    }

    public WeeklyReportValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
