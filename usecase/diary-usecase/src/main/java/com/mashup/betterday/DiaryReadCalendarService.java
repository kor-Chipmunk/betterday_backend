package com.mashup.betterday;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.user.model.User;
import com.mashup.port.DiaryPort;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryReadCalendarService implements DiaryReadCalendarUsecase {

    private final DiaryPort diaryPort;

    @Override
    public List<Diary> read(User user, LocalDateTime from, LocalDateTime until) {
        return diaryPort.findAllByCalendar(user.getId(), from, until);
    }

}
