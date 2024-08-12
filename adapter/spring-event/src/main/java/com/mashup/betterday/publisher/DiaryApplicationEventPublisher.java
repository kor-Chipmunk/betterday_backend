package com.mashup.betterday.publisher;

import com.mashup.betterday.event.application.DiaryCreatedApplicationEvent;
import com.mashup.port.DiaryEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiaryApplicationEventPublisher implements DiaryEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void publishCreatedEvent(DiaryCreatedApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
