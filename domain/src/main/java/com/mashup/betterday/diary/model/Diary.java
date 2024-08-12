package com.mashup.betterday.diary.model;

import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.user.model.UserId;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    private ZonedDateTime writtenAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Diary write(
            DiaryId id,
            Content content,
            UserId userId,
            Weather weather,
            ZonedDateTime writtenAt
    ) throws DiaryValidationException {
        return new Diary(
                id,
                content,
                userId,
                weather,
                writtenAt,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static Diary write(
            DiaryId id,
            Content content,
            UserId userId,
            Weather weather
    ) throws DiaryValidationException {
        return Diary.write(
                id,
                content,
                userId,
                weather,
                ZonedDateTime.now()
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
