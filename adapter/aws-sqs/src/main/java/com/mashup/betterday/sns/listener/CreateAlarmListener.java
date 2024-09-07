package com.mashup.betterday.sns.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class CreateAlarmListener {

    @SqsListener("AlarmMusicGenerationQueue")
    public void messageListener(String message) {
        System.out.println("Listener: " + message);
    }

}
