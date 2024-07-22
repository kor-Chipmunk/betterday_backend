package com.mashup.betterday.model.alarm;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AlarmDto {
    private final Long id;
    private final String link;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static AlarmDto from(Alarm alarm) {
        return new AlarmDto(
                alarm.getId().getValue(),
                alarm.getLink().getLink(),
                alarm.getCreatedAt(),
                alarm.getUpdatedAt()
        );
    }
}
