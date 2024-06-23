package com.mashup.feelring.controller;

import com.mashup.feelring.FcmSendUsecase;
import com.mashup.feelring.model.fcm.FcmMessageDto;
import com.mashup.feelring.model.fcm.FcmPushRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {
    private final FcmSendUsecase fcmSendUsecase;

    public ResponseEntity<FcmMessageDto> pushMessage(@RequestBody FcmPushRequest fcmPushRequest) {
        return ResponseEntity.ok(null);
    }
}
