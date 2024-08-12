package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import java.util.List;

public interface DiaryReadUsecase {
    List<Diary> read(User user, int page, int size);

    Diary read(User user, String diaryUid);

    Diary read(String diaryUid);
}
