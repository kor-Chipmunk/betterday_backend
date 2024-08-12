package com.mashup.betterday.model.diary;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.alarm.AlarmDto;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DiaryDto {
    private final Long id;
    private final String uid;
    private final Long userId;
    private final String content;
    private final String weather;
    private final ZonedDateTime writtenAt;
    private final LocalDateTime createdAt;

    private AlarmDto alarm = null;

    public static DiaryDto from(Diary diary) {
        return new DiaryDto(
                diary.getId().getValue(),
                diary.getId().getUid().toString(),
                diary.getUserId().getValue(),
                diary.getContent().getContent(),
                diary.getWeather().name(),
                diary.getWrittenAt(),
                diary.getCreatedAt()
        );
    }

    public DiaryDto withAlarm(Alarm alarm) {
        this.alarm = AlarmDto.from(alarm);
        return this;
    }
}
