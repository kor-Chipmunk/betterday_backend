package com.mashup.betterday.diary;

import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.UserId;
import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DiaryEntityConverter {

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(
                diary.getId().getValue(),
                diary.getId().getUid().toString(),
                diary.getContent().getContent(),
                diary.getUserId().getValue(),
                diary.getWeather(),
                diary.getWrittenAt(),
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
                diaryEntity.getCategory(),
                diaryEntity.getWrittenAt(),
                diaryEntity.getCreatedAt(),
                diaryEntity.getUpdatedAt(),
                diaryEntity.getDeletedAt()
        );
    }

}
