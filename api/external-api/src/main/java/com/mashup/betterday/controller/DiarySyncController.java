package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.DiarySyncBatchUsecase;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.diary.DiaryDto;
import com.mashup.betterday.model.diary.DiarySyncBatchRequest;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "일기 동기화", description = "일기 동기화 API")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/diaries/sync")
public class DiarySyncController {

    private final DiarySyncBatchUsecase diarySyncBatchUsecase;

    @Operation(summary = "일기 동기화", description = "일기 UID를 기준으로 새로운 일기는 생성하고, 기존 일기는 수정합니다.")
    @PostMapping
    ResponseEntity<List<DiaryDto>> syncBatch(
            @RequestBody DiarySyncBatchRequest request,
            @AuthUser User user
    ) {
        List<DiarySyncBatchUsecase.Request.CreateItem> items = request.getDiaries().stream()
                .map(diary -> new DiarySyncBatchUsecase.Request.CreateItem(
                        diary.getUid(),
                        user.getId().getValue(),
                        diary.getContent(),
                        diary.getWeather()
                )).toList();
        List<Diary> createdDiaries = diarySyncBatchUsecase.syncBatch(
                new DiarySyncBatchUsecase.Request(items)
        );
        List<DiaryDto> response = createdDiaries.stream()
                .map(DiaryDto::from)
                .toList();
        return ResponseEntity.ok(response);
    }

}
