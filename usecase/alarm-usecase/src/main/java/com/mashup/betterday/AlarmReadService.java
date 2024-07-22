package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.User;
import com.mashup.port.AlarmPort;
import com.mashup.port.DiaryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmReadService implements AlarmReadUsecase {

    private final AlarmPort alarmPort;

    @Override
    public Alarm read(Diary diary) {
        return alarmPort.findByDiary(diary.getId());
    }

    @Override
    public List<Alarm> read(List<Diary> diaries) {
        List<DiaryId> diaryIds = diaries.stream().map(Diary::getId).toList();
        return alarmPort.findAllByDiaries(diaryIds);
    }
}
