package com.mashup.betterday.controller;

import com.mashup.betterday.AlarmReadUsecase;
import com.mashup.betterday.AuthUser;
import com.mashup.betterday.DiaryCreateUsecase;
import com.mashup.betterday.DiaryDeleteUsecase;
import com.mashup.betterday.DiaryReadUsecase;
import com.mashup.betterday.DiaryUpdateUsecase;
import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.diary.DiaryCreateRequest;
import com.mashup.betterday.model.diary.DiaryDto;
import com.mashup.betterday.model.diary.DiaryUpdateRequest;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "일기", description = "일기 API")
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

    @Operation(summary = "일기 목록 조회", description = "일기 목록을 조회합니다.")
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

    @Operation(summary = "일기 조회", description = "일기 UID로 조회합니다.")
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

    @Operation(summary = "일기 생성", description = "일기를 생성합니다.")
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
                        request.getWrittenAt(),
                        user
                )
        );
        return ResponseEntity.ok(DiaryDto.from(createdDiary));
    }

    @Operation(summary = "일기 수정", description = "일기 UID로 수정합니다.")
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

    @Operation(summary = "일기 삭제", description = "일기 UID로 삭제합니다.")
    @DeleteMapping("/{diaryUId}")
    ResponseEntity<DiaryDto> delete(
            @PathVariable("diaryUId") String uid,
            @AuthUser User user
    ) {
        Diary deletedDiary = diaryDeleteUsecase.delete(
                new DiaryDeleteUsecase.Request(uid, user)
        );
        return ResponseEntity.ok(DiaryDto.from(deletedDiary));
    }
}
