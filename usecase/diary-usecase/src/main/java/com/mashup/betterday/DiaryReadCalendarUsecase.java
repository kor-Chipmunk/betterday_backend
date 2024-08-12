package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.user.model.User;
import java.time.LocalDateTime;
import java.util.List;

public interface DiaryReadCalendarUsecase {
    List<Diary> read(User user, LocalDateTime from, LocalDateTime until);
}
