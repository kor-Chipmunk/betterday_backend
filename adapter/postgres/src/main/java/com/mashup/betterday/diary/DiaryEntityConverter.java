package com.mashup.betterday.diary;

import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.user.model.UserId;
import java.util.UUID;

public class DiaryEntityConverter {

    private DiaryEntityConverter() {}

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(
                diary.getId().getValue(),
                diary.getId().getUid().toString(),
                diary.getContent().getContent(),
                diary.getUserId().getValue(),
                diary.getWeather().name(),
                diary.getCreatedAt(),
                diary.getUpdatedAt(),
                diary.getDeletedAt()
        );
    }

    public static Diary toModel(DiaryEntity diaryEntity) {
        return new Diary(
                new DiaryId(diaryEntity.getId(), UUID.fromString(diaryEntity.getUid())),
                new Content(diaryEntity.getContent()),
                new UserId(diaryEntity.getUserId()),
                Weather.from(diaryEntity.getCategory()),
                diaryEntity.getCreatedAt(),
                diaryEntity.getUpdatedAt(),
                diaryEntity.getDeletedAt()
        );
    }

}
