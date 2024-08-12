package com.mashup.feelring.diary;

import com.mashup.feelring.diary.model.Diary;
import com.mashup.feelring.diary.model.DiaryId;
import com.mashup.feelring.diary.model.Weather;
import com.mashup.feelring.user.model.UserId;
import java.util.UUID;

public class DiaryEntityConverter {

    private DiaryEntityConverter() {}

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(
                diary.getId().getValue(),
                diary.getId().getUid().toString(),
                diary.getContent(),
                diary.getUserId().getValue(),
                diary.getWeather().name(),
                diary.getAlarmUrl(),
                null,
                diary.getCreatedAt(),
                diary.getUpdatedAt(),
                diary.getDeletedAt()
        );
    }

    public static Diary toModel(DiaryEntity diaryEntity) {
        return new Diary(
                new DiaryId(diaryEntity.getId(), UUID.fromString(diaryEntity.getUid())),
                diaryEntity.getContent(),
                new UserId(diaryEntity.getUserId()),
                Weather.from(diaryEntity.getCategory()),
                diaryEntity.getAlarmUrl(),
                diaryEntity.getCreatedAt(),
                diaryEntity.getUpdatedAt(),
                diaryEntity.getDeletedAt()
        );
    }

}
