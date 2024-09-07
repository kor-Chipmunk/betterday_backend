package com.mashup.betterday.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreatePredictionResponse {

    @Pattern(regexp = "^(http|https)://.*$")
    private String output; // https://replicate.delivery/pbxt/OeLYIQiltdzMaCex1shlEFy6...

}
