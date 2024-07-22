package com.mashup.betterday;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.fcm.exception.FCMTokenValidationException;
import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.fcm.model.FCMId;
import com.mashup.betterday.fcm.model.FCMToken;
import com.mashup.port.FcmPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmRegisterService implements FcmRegisterUsecase {

    private final FcmPort fcmPort;

    @Override
    public FCM register(Request request) {
        try {
            FCM registeredFCM = FCM.register(
                    FCMId.empty(),
                    request.getUser().getId(),
                    new FCMToken(request.getToken())
            );

            return fcmPort.save(registeredFCM);
        } catch (FCMTokenValidationException exception) {
            throw BusinessException.from(ErrorCode.FCM_CREATE_FAILED);
        }
    }
}
