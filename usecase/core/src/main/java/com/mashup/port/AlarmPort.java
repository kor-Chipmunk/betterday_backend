package com.mashup.port;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.diary.model.DiaryId;
import java.util.List;

public interface AlarmPort {
    Alarm save(Alarm alarm);
    Alarm findById(AlarmId id);
    Alarm findByDiary(DiaryId diaryId);
    List<Alarm> findAllByDiaries(List<DiaryId> diaryIds);
    boolean isExist(DiaryId diaryId);
}
