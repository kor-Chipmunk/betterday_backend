package com.mashup.betterday.alarm;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.UserId;

public class AlarmEntityConverter {

    private AlarmEntityConverter() {}

    public static AlarmEntity toEntity(Alarm alarm) {
        return new AlarmEntity(
                alarm.getId().getValue(),
                alarm.getLink().getLink(),
                alarm.getDiaryId().getValue(),
                alarm.getCreatedAt(),
                alarm.getUpdatedAt(),
                alarm.getDeletedAt()
        );
    }

    public static Alarm toModel(AlarmEntity alarmEntity) {
        return new Alarm(
                new AlarmId(alarmEntity.getId()),
                new MusicLink(alarmEntity.getLink()),
                DiaryId.withId(alarmEntity.getDiaryId()),
                alarmEntity.getCreatedAt(),
                alarmEntity.getUpdatedAt(),
                alarmEntity.getDeletedAt()
        );
    }

}
