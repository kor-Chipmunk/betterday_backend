package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.*;
import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.diary.*;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/diaries")
public class DiaryController {

    private final DiaryCreateUsecase diaryCreateUsecase;
    private final DiaryReadUsecase diaryReadUsecase;
    private final DiaryUpdateUsecase diaryUpdateUsecase;
    private final DiaryDeleteUsecase diaryDeleteUsecase;

    private final AlarmReadUsecase alarmReadUsecase;

    @GetMapping
    ResponseEntity<List<DiaryDto>> read(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @AuthUser User user
    ) {
        List<Diary> diaries = diaryReadUsecase.read(user, page, size);
        List<Alarm> alarms = alarmReadUsecase.read(diaries);

        return ResponseEntity.ok(
                diaries.stream()
                        .map(diary -> {
                            var diaryDto = DiaryDto.from(diary);
                            for (var alarm : alarms) {
                                if (Objects.equals(
                                        diary.getId().getValue(),
                                        alarm.getDiaryId().getValue()
                                )) {
                                    return diaryDto.withAlarm(alarm);
                                }
                            }
                            return diaryDto;
                        })
                        .toList()
        );
    }

    @GetMapping("/{diaryUId}")
    ResponseEntity<DiaryDto> readOne(
            @PathVariable(name = "diaryUId") String uid,
            @AuthUser User user
    ) {
        Diary diary = diaryReadUsecase.read(user, uid);
        Alarm alarm = alarmReadUsecase.read(diary);

        return ResponseEntity.ok(
                DiaryDto.from(diary)
                        .withAlarm(alarm)
        );
    }

    @PostMapping
    ResponseEntity<DiaryDto> create(
            @RequestBody DiaryCreateRequest request,
            @AuthUser User user
    ) {
        Diary createdDiary = diaryCreateUsecase.create(
                new DiaryCreateUsecase.Request(
                        request.getUid(),
                        request.getContent(),
                        request.getWeather(),
                        user
                )
        );
        return ResponseEntity.ok(DiaryDto.from(createdDiary));
    }

    @PutMapping("/{diaryUId}")
    ResponseEntity<DiaryDto> update(
            @PathVariable("diaryUId") String uid,
            @RequestBody DiaryUpdateRequest request,
            @AuthUser User user
    ) {
        Diary updatedDiary = diaryUpdateUsecase.update(
                new DiaryUpdateUsecase.Request(
                        uid,
                        request.getContent(),
                        request.getWeather(),
                        user
                )
        );
        return ResponseEntity.ok(DiaryDto.from(updatedDiary));
    }

    @DeleteMapping("/{diaryUId}")
    ResponseEntity<DiaryDto> delete(
            @PathVariable("diaryUId") String uid,
            @AuthUser User user
    ) {
        Diary deletedDiary = diaryDeleteUsecase.delete(
                new DiaryDeleteUsecase.Request(uid)
        );
        return ResponseEntity.ok(DiaryDto.from(deletedDiary));
    }
}
