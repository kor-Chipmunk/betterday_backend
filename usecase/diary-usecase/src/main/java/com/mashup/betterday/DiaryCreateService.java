package com.mashup.betterday;

import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.DiaryPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryCreateService implements DiaryCreateUsecase {

    private final DiaryPort diaryPort;

    @Override
    public Diary create(Request request) {
        try {
            Diary writtenDiary = Diary.write(
                    DiaryId.withUid(request.getUid()),
                    new Content(request.getContent()),
                    request.getUser().getId(),
                    Weather.from(request.getWeather())
            );

            return diaryPort.save(writtenDiary);
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_CREATE_FAILED);
        }
    }
}
