package com.mashup.port;

import com.mashup.betterday.event.application.DiaryCreatedApplicationEvent;

public interface DiaryEventPort {
    void publishCreatedEvent(DiaryCreatedApplicationEvent event);
}
