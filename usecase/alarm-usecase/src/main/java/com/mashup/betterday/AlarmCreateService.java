package com.mashup.betterday;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.common.link.exception.LinkValidationException;
import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.AlarmPort;
import com.mashup.port.DiaryPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmCreateService implements AlarmCreateUsecase {

    // duplicated 체크

    private final AlarmPort alarmPort;

    @Override
    public Alarm create(Request request) {
        DiaryId diaryId = DiaryId.withId(request.getDiaryId());
        validationDuplicated(diaryId);

        try {
            Alarm registeredAlarm = Alarm.register(
                    new AlarmId(0L),
                    new MusicLink(request.getLink()),
                    diaryId
            );

            return alarmPort.save(registeredAlarm);
        } catch (LinkValidationException exception) {
            throw BusinessException.from(ErrorCode.ALARM_CREATE_FAILED);
        }
    }

    private void validationDuplicated(DiaryId diaryId) {
        if (alarmPort.isExist(diaryId)) {
            throw BusinessException.from(ErrorCode.ALARM_DUPLICATED);
        }
    }
}
