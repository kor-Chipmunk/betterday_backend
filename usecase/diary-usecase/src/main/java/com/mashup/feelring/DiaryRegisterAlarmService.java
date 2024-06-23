package com.mashup.feelring;

import com.mashup.feelring.diary.exception.DiaryValidationException;
import com.mashup.feelring.diary.model.Diary;
import com.mashup.feelring.diary.model.Weather;
import com.mashup.feelring.exception.BusinessException;
import com.mashup.feelring.exception.ErrorCode;
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
