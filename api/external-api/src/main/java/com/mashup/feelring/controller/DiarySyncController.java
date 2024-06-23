package com.mashup.feelring.controller;

import com.mashup.feelring.AuthUser;
import com.mashup.feelring.*;
import com.mashup.feelring.diary.model.Diary;
import com.mashup.feelring.model.diary.*;
import com.mashup.feelring.user.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<DiaryDto> response = createdDiaries.stream().map(diary -> DiaryDto.from(diary, user)).toList();
        return ResponseEntity.ok(response);
    }

}
