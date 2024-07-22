package com.mashup.betterday.fcm;

import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.fcm.model.FCMId;
import com.mashup.betterday.fcm.model.FCMToken;
import com.mashup.betterday.user.model.UserId;

public class FcmEntityConverter {

    private FcmEntityConverter() {}

    public static FcmEntity toEntity(FCM fcm) {
        return new FcmEntity(
                fcm.getId().getValue(),
                fcm.getUserId().getValue(),
                fcm.getToken().getValue(),
                fcm.getCreatedAt(),
                fcm.getUpdatedAt(),
                fcm.getDeletedAt()
        );
    }

    public static FCM toModel(FcmEntity fcmEntity) {
        return new FCM(
                new FCMId(fcmEntity.getId()),
                new UserId(fcmEntity.getUserId()),
                new FCMToken(fcmEntity.getToken()),
                fcmEntity.getCreatedAt(),
                fcmEntity.getUpdatedAt(),
                fcmEntity.getDeletedAt()
        );
    }

}
