package com.mashup.betterday;

import com.mashup.port.FcmPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmPushService implements FcmPushUsecase {

    private final FcmPort fcmPort;

    @Override
    public Response pushMessage(Request request) {
        return null;
    }
}
