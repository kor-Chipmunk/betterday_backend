package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.port.DiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryRegisterAlarmService implements DiaryRegisterAlarmUsecase {

    private final DiaryPort diaryPort;

    @Override
    public Diary registerAlarm(Request request) {
        Diary registeredAlarmDiary = diaryPort.findByUid(request.getUid());
        registeredAlarmDiary.registerAlarm(request.getAlarmUrl());
        return diaryPort.save(registeredAlarmDiary);
    }
}
