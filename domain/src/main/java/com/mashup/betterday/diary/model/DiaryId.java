package com.mashup.betterday.diary.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DiaryId {

    private Long value;
    private UUID uid;

}
