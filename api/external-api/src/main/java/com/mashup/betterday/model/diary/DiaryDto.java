package com.mashup.betterday.model.diary;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import java.time.LocalDateTime;
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
    private final LocalDateTime createdAt;

    public static DiaryDto from(Diary diary, User user) {
        return new DiaryDto(
                diary.getId().getValue(),
                diary.getId().getUid().toString(),
                user.getId().getValue(),
                diary.getContent(),
                diary.getWeather().name(),
                diary.getCreatedAt()
        );
    }
}
