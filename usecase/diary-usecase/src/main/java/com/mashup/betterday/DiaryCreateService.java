package com.mashup.betterday;

import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.event.application.DiaryCreatedApplicationEvent;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.port.DiaryEventPort;
import com.mashup.port.DiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryCreateService implements DiaryCreateUsecase {

    private final DiaryPort diaryPort;
    private final DiaryEventPort diaryEventPort;

    @Override
    public Diary create(Request request) {
        try {
            Diary writtenDiary = Diary.write(
                    DiaryId.withUid(request.getUid()),
                    new Content(request.getContent()),
                    request.getUser().getId(),
                    Weather.from(request.getWeather()),
                    request.getWrittenAt()
            );
            diaryEventPort.publishCreatedEvent(
                    new DiaryCreatedApplicationEvent(
                            request.getUid(),
                            request.getContent()
                    )
            );
            return diaryPort.save(writtenDiary);
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_CREATE_FAILED);
        }
    }
}
