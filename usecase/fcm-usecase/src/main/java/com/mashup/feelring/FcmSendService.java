package com.mashup.feelring;

import com.mashup.port.FcmPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmSendService implements FcmSendUsecase {

    private final FcmPort fcmPort;

    @Override
    public Response pushMessage(Request request) {
        return null;
    }
}
