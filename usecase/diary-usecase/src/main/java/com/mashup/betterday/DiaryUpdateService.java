package com.mashup.betterday;

import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
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
        Diary updatedDiary = diaryPort.findByUid(DiaryId.withUid(request.getUid()));

        if (!updatedDiary.getUserId().equals(request.getUser().getId())) {
            throw BusinessException.from(ErrorCode.DIARY_NOT_FOUND);
        }

        try {
            updatedDiary.edit(
                    new Content(request.getContent()),
                    Weather.from(request.getWeather())
            );
            return updatedDiary;
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_CREATE_FAILED);
        }
    }
}
