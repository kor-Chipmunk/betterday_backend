package com.mashup.betterday.controller;

import com.mashup.betterday.AlarmCreateUsecase;
import com.mashup.betterday.DiaryReadUsecase;
import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.model.alarm.AlarmCreateRequest;
import com.mashup.betterday.model.alarm.AlarmDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/alarms")
public class AlarmController {

    private final AlarmCreateUsecase alarmCreateUsecase;
    private final DiaryReadUsecase diaryReadUsecase;

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
