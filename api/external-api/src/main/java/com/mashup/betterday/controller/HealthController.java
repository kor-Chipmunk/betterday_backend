package com.mashup.betterday.controller;

import com.mashup.betterday.response.GlobalResponse;
import com.mashup.betterday.response.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping
    ResponseEntity<Object> healthCheck() {
        return ResponseEntity.ok(GlobalResponse.from(ResponseCode.OK, "OK"));
    }

}
