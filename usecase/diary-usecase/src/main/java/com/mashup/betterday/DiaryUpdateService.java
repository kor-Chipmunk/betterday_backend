package com.mashup.betterday;

import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
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
