package com.mashup.port;

import com.mashup.feelring.diary.model.Diary;
import java.util.List;

public interface DiaryPort {
    Diary save(Diary diary);
    List<Diary> saveAll(List<Diary> diaries);
    List<Diary> findAll(Long userId, int page, int size);
    Diary findByUid(String uid);
    Diary delete(Diary diary);
    List<Diary> findAll(List<String> uids);
}
