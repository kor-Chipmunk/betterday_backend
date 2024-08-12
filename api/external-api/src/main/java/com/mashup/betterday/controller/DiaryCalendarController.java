package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.DiaryReadCalendarUsecase;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.diarycalendar.DiaryCalendarDto;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/diaries/calendar")
public class DiaryCalendarController {

    private final DiaryReadCalendarUsecase diaryReadCalendarUsecase;

    @GetMapping
    ResponseEntity<List<DiaryCalendarDto>> readCalender(
            @RequestParam(name = "year") LocalDateTime year,
            @RequestParam(name = "month") LocalDateTime month,
            @AuthUser User user
    ) {
        List<Diary> diaries = diaryReadCalendarUsecase.read(user, year, month);

        return ResponseEntity.ok(
                diaries.stream()
                        .map(DiaryCalendarDto::from)
                        .toList()
        );
    }
}
