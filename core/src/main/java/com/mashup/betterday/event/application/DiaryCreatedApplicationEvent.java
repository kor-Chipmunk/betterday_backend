package com.mashup.betterday.event.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiaryCreatedApplicationEvent {
    private final String diaryUid;
    private final String content;
}
