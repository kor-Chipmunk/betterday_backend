package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.FcmPushUsecase;
import com.mashup.betterday.FcmRegisterUsecase;
import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.model.fcm.*;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {
    private final FcmRegisterUsecase fcmRegisterUsecase;
    private final FcmPushUsecase fcmPushUsecase;

    @PostMapping
    public ResponseEntity<FcmDto> registerToken(
            @RequestBody FcmRegisterRequest fcmRegisterRequest,
            @AuthUser User user
    ) {
        final FCM fcm = fcmRegisterUsecase.register(
                new FcmRegisterUsecase.Request(
                        user,
                        fcmRegisterRequest.getToken()
                )
        );
        return ResponseEntity.ok(FcmDto.from(fcm));
    }

    @PostMapping("/push")
    public ResponseEntity<FcmMessageDto> pushMessage(@RequestBody FcmPushRequest fcmPushRequest) {
        return ResponseEntity.ok(null);
    }
}
