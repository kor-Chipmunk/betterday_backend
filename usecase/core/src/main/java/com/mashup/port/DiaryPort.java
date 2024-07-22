package com.mashup.port;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.UserId;
import java.util.List;

public interface DiaryPort {
    Diary save(Diary diary);
    List<Diary> saveAll(List<Diary> diaries);
    List<Diary> findAllByUid(UserId userId, int page, int size);
    Diary findByUid(DiaryId id);
    Diary delete(Diary diary);
    List<Diary> findAllByUid(List<DiaryId> uids);
}
