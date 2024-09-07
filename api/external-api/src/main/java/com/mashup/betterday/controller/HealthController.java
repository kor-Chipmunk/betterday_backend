package com.mashup.betterday.controller;

import com.mashup.betterday.response.GlobalResponse;
import com.mashup.betterday.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "헬스체크", description = "헬스체크 API")
@RestController
@RequestMapping("/")
public class HealthController {

    @Operation(summary = "헬스체크", description = "서버 상태를 확인합니다.")
    @GetMapping
    ResponseEntity<Object> healthCheck() {
        return ResponseEntity.ok(GlobalResponse.from(ResponseCode.OK, "OK"));
    }

}
