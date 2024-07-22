package com.mashup.betterday.diary.model;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.user.model.UserId;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Diary {

    private DiaryId id;
    private Content content;

    private UserId userId;

    private Weather weather;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Diary write(
            DiaryId id,
            Content content,
            UserId userId,
            Weather weather
    ) throws DiaryValidationException {
        return new Diary(
                id,
                content,
                userId,
                weather,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public void edit(
            Content content,
            Weather weather
    ) throws DiaryValidationException {
        this.content = content;
        this.weather = weather;

        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
