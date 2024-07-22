package com.mashup.betterday;

import com.mashup.port.FcmPushPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmPushService implements FcmPushUsecase {

    private final FcmPushPort fcmPushPort;

    @Override
    public Response pushMessage(Request request) {
        return null;
    }
}
