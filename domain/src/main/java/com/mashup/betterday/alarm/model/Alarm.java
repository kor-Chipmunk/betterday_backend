package com.mashup.betterday.alarm.model;

import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.model.DiaryId;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Alarm {

    private AlarmId id;
    private MusicLink link;
    private DiaryId diaryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Alarm register(
            AlarmId id,
            MusicLink link,
            DiaryId diaryId
    ) {
        return new Alarm(
                id,
                link,
                diaryId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public void edit(MusicLink link) {
        this.link = link;
    }

}
