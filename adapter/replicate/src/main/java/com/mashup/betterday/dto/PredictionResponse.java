package com.mashup.betterday.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
public class PredictionResponse {

    private String id;
    private String model;
    private String version;
    private CreatePredictionRequest.Input input;
    private String logs;
    private String output;
    private String error;
    private PredictionStatus status;
    private LocalDateTime createdAt;
    private Boolean dataRemoved;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Metrics metrics;
    private URLs urls;

    @ToString
    @RequiredArgsConstructor
    public enum PredictionStatus {
        STARTING("starting"),
        PROCESSING("processing"),
        SUCCEEDED("succeeded"),
        FAILED("failed"),
        CANCELED("canceled"),
        ;

        private final String value;
    }

    public static class Metrics {

        private Double predictTime;

    }

    public static class URLs {

        private String cancel;
        private String get;
    }

}
