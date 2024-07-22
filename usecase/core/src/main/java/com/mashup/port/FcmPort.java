package com.mashup.port;

import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.fcm.model.FCMId;

public interface FcmPort {
    FCM save(FCM user);
    FCM findById(FCMId id);
}
