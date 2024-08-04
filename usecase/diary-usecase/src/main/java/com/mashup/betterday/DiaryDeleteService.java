package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.port.DiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryDeleteService implements DiaryDeleteUsecase {

    private final DiaryPort diaryPort;

    @Override
    public Diary delete(Request request) {
        Diary deletedDiary = diaryPort.findByUid(DiaryId.withUid(request.getUid()));

        if (!deletedDiary.getUserId().equals(request.getUser().getId())) {
            throw BusinessException.from(ErrorCode.DIARY_NOT_FOUND);
        }

        deletedDiary = diaryPort.delete(deletedDiary);
        return deletedDiary;
    }
}
