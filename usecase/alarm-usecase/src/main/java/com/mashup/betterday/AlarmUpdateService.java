package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.port.AlarmPort;
import com.mashup.port.DiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmUpdateService implements AlarmUpdateUsecase {

    private final AlarmPort alarmPort;

    @Override
    public Alarm update(Request request) {
        Alarm updatedAlarm = alarmPort.findById(new AlarmId(request.getAlarmId()));

        try {
            updatedAlarm.edit(
                    new MusicLink(request.getLink())
            );
            return updatedAlarm;
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.ALARM_CREATE_FAILED);
        }
    }
}
