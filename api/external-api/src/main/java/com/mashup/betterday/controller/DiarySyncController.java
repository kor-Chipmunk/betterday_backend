package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.DiarySyncBatchUsecase;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.diary.DiaryDto;
import com.mashup.betterday.model.diary.DiarySyncBatchRequest;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/diaries/sync")
public class DiarySyncController {

    private final DiarySyncBatchUsecase diarySyncBatchUsecase;

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
