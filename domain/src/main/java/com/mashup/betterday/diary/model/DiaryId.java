package com.mashup.betterday.diary.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DiaryId {

    private Long value;
    private UUID uid;

    public static DiaryId withId(Long id) {
        return new DiaryId(id, null);
    }

    public static DiaryId withUid(String uid) {
        return new DiaryId(0L, UUID.fromString(uid));
    }

    public static DiaryId empty() {
        return new DiaryId(0L, UUID.randomUUID());
    }

}
