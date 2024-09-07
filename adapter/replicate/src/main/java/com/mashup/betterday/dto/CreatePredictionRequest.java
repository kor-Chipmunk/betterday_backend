package com.mashup.betterday.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mashup.betterday.config.properties.ReplicateProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@JsonNaming(SnakeCaseStrategy.class)
@Builder
public class CreatePredictionRequest {

    @NotBlank
    private String version;

    @NotNull
    private Input input;

    @JsonNaming(SnakeCaseStrategy.class)
    @Builder
    public static class Input {

        private Integer seed;

        @NotNull
        private Integer topK = 250;

        @NotNull
        private Double topP = 0.0;

        @NotBlank
        private String prompt;

        @NotNull
        private Integer duration = 8;

        @Pattern(regexp = "^(http|https)://.*$")
        private String inputAudio;

        @NotNull
        private Double temperature = 1.0;

        @NotNull
        private Boolean continuation = false;

        @NotNull
        private ModelVersion modelVersion = ModelVersion.STEREO_MELODY_LARGE;

        @NotNull
        private OutputFormat outputFormat = OutputFormat.WAV;

        private Integer continuationEnd;

        @NotNull
        private Integer continuationStart = 0;

        @NotNull
        private Boolean multiBandDiffusion = false;

        @NotNull
        private NormalizationStrategy normalizationStrategy = NormalizationStrategy.LOUDNESS;

        @NotNull
        private Integer classifierFreeGuidance = 3;

        // Enum for model_version
        @ToString
        @RequiredArgsConstructor
        public enum ModelVersion {
            STEREO_MELODY_LARGE("stereo-melody-large"),
            STEREO_LARGE("stereo-large"),
            MELODY_LARGE("melody-large"),
            LARGE("large");

            private final String value;
        }

        // Enum for output_format
        @ToString
        @RequiredArgsConstructor
        public enum OutputFormat {
            WAV("wav"),
            MP3("mp3");

            private final String value;
        }

        // Enum for normalization_strategy
        @ToString
        @RequiredArgsConstructor
        public enum NormalizationStrategy {
            LOUDNESS("loudness"),
            CLIP("clip"),
            PEAK("peak"),
            RMS("rms");

            private final String value;
        }

    }

    @Component
    @RequiredArgsConstructor
    public class CreatePredictionRequestFactory {

        private final ReplicateProperties replicateProperties;

        /**
         * ReqeustProperties에서 가져온 version을 사용하여 CreatePredictionRequest를 생성합니다.
         *
         * @return CreatePredictionRequest
         */
        public CreatePredictionRequest.CreatePredictionRequestBuilder builderWithVersion() {
            String version = replicateProperties.getVersion();
            return CreatePredictionRequest.builder()
                    .version(version);
        }

    }

}
