package com.mashup.betterday.fcm.model;

import com.mashup.betterday.user.model.UserId;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class FCM {

    private FCMId id;
    private UserId userId;
    private FCMToken token;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static FCM register(
            FCMId id,
            UserId userId,
            FCMToken token
    ) {
        return new FCM(
                id,
                userId,
                token,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }
}
