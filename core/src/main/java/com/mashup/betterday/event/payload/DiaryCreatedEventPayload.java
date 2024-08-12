package com.mashup.betterday.event.payload;

import com.mashup.betterday.event.application.DiaryCreatedApplicationEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiaryCreatedEventPayload {

    private final String diaryUid;
    private final String prompt;

    public static DiaryCreatedEventPayload from(DiaryCreatedApplicationEvent event) {
        return new DiaryCreatedEventPayload(
                event.getDiaryUid(),
                event.getContent()
        );
    }

}
