package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.User;
import com.mashup.port.DiaryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryReadService implements DiaryReadUsecase {

    private final DiaryPort diaryPort;

    @Override
    public List<Diary> read(User user, int page, int size) {
        return diaryPort.findAllByUserId(user.getId(), page, size);
    }

    @Override
    public Diary read(User user, String diaryUid) {
        return diaryPort.findByUserIdAndUid(user.getId(), DiaryId.withUid(diaryUid));
    }

    @Override
    public Diary read(String diaryUid) {
        return diaryPort.findByUid(DiaryId.withUid(diaryUid));
    }
}
