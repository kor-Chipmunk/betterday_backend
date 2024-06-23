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
public class DiaryUpdateService implements DiaryUpdateUsecase {

    private final DiaryPort diaryPort;

    @Override
    public Diary update(Request request) {
        Diary updatedDiary = diaryPort.findByUid(request.getUid());

        try {
            updatedDiary.edit(
                    request.getContent(),
                    Weather.from(request.getWeather())
            );
            return updatedDiary;
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_FAILED);
        }
    }
}
