package com.mashup.betterday.model.fcm;

import lombok.Data;

@Data
public class FcmMessageDto {
    private boolean validateOnly;
    private FcmMessageDto.Message message;

    @Data
    public static class Message {
        private FcmMessageDto.Notification notification;
        private String token;
    }

    @Data
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
