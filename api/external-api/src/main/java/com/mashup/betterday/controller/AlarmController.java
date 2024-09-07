package com.mashup.betterday.controller;

import com.mashup.betterday.AlarmCreateUsecase;
import com.mashup.betterday.DiaryReadUsecase;
import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.alarm.AlarmCreateRequest;
import com.mashup.betterday.model.alarm.AlarmDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "알람", description = "알람 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/alarms")
public class AlarmController {

    private final AlarmCreateUsecase alarmCreateUsecase;
    private final DiaryReadUsecase diaryReadUsecase;

    @Operation(summary = "알람 생성", description = "AWS SQS Consumer 컴포넌트가 호출할 알람 생성 API")
    @PostMapping
    ResponseEntity<AlarmDto> create(
            @RequestBody AlarmCreateRequest request
    ) {
        Diary diary = diaryReadUsecase.read(request.getDiaryUid());

        Alarm createdAlarm = alarmCreateUsecase.create(
                new AlarmCreateUsecase.Request(
                        diary.getId().getValue(),
                        request.getLink()
                )
        );
        return ResponseEntity.ok(AlarmDto.from(createdAlarm));
    }

}
