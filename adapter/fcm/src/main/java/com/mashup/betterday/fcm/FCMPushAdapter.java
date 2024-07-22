package com.mashup.betterday.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.mashup.port.FcmPushPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FCMPushAdapter implements FcmPushPort {

    private final FirebaseMessaging fcm;

    @Override
    public int pushMessage(String title, String token, String to) throws RuntimeException {
        Message msg = Message.builder()
                .setToken(token)
                .putData("body", title)
                .build();

        try {
            String id = fcm.send(msg);
            return Integer.parseInt(id);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
