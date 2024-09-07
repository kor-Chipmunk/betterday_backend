package com.mashup.betterday.api;

import com.mashup.betterday.config.OpenFeignConfig;
import com.mashup.betterday.dto.CreatePredictionRequest;
import com.mashup.betterday.dto.CreatePredictionResponse;
import com.mashup.betterday.dto.PredictionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "prediction",
        url = "${replicate.api.endpoint.base}",
        configuration = OpenFeignConfig.class
)
public interface PredictionApi {

    @PostMapping(
            value = "${replicate.api.endpoint.create}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CreatePredictionResponse createPrediction(@RequestBody CreatePredictionRequest request);

    @GetMapping(
            value = "${replicate.api.endpoint.get}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    PredictionResponse getPrediction(@PathVariable String predictionId);

    @PostMapping(
            value = "${replicate.api.endpoint.cancel}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    PredictionResponse cancelPrediction(@PathVariable String predictionId);

}
