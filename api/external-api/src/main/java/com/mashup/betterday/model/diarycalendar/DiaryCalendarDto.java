package com.mashup.betterday.model.diarycalendar;

import com.mashup.betterday.diary.model.Diary;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DiaryCalendarDto {
    private final String uid;
    private final ZonedDateTime writtenAt;
    private final LocalDateTime createdAt;

    public static DiaryCalendarDto from(Diary diary) {
        return new DiaryCalendarDto(
                diary.getId().getUid().toString(),
                diary.getWrittenAt(),
                diary.getCreatedAt()
        );
    }
}
