package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import java.util.List;

public interface AlarmReadUsecase {
    Alarm read(Diary diary);
    List<Alarm> read(List<Diary> diaries);
}
