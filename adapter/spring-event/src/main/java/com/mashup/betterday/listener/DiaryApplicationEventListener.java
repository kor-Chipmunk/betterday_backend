package com.mashup.betterday.listener;

import com.mashup.betterday.event.application.DiaryCreatedApplicationEvent;
import com.mashup.betterday.event.payload.DiaryCreatedEventPayload;
import com.mashup.betterday.sns.publisher.DiarySnsPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiaryApplicationEventListener {

    private static final String EVENT_HANDLER_TASK_EXECUTOR = "diaryTaskExecutor";

    private final DiarySnsPublisher publisher;

    @Async(EVENT_HANDLER_TASK_EXECUTOR)
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCreatedEvent(DiaryCreatedApplicationEvent event) {
        DiaryCreatedEventPayload payload = DiaryCreatedEventPayload.from(event);
        publisher.publish(payload);
    }

}
