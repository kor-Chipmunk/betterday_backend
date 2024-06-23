package com.mashup.feelring;

import com.mashup.feelring.DiarySyncBatchUsecase.Request.CreateItem;
import com.mashup.feelring.diary.exception.DiaryValidationException;
import com.mashup.feelring.diary.model.Diary;
import com.mashup.feelring.diary.model.DiaryId;
import com.mashup.feelring.diary.model.Weather;
import com.mashup.feelring.exception.BusinessException;
import com.mashup.feelring.exception.ErrorCode;
import com.mashup.feelring.user.model.UserId;
import com.mashup.port.DiaryPort;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiarySyncBatchService implements DiarySyncBatchUsecase {

    private final DiaryPort diaryPort;

    @Override
    public List<Diary> syncBatch(Request request) {
        try {
            List<Diary> existDiaries = diaryPort.findAll(
                    request.getRequests().stream().map(CreateItem::getUid).toList());
            Set<String> uids = existDiaries.stream().map(diary -> diary.getId().getUid().toString())
                    .collect(Collectors.toUnmodifiableSet());

            List<Diary> updatedDiaries = request.getRequests().stream()
                    .filter(diaryRequest -> uids.contains(diaryRequest.getUid()))
                    .map(diaryRequest -> {
                        Diary existDiary = existDiaries.stream().filter(diary -> diary.getId().getUid().toString().equals(diaryRequest.getUid())).findFirst()
                                .orElse(null);
                        if (existDiary == null) return null;

                        existDiary.edit(diaryRequest.getContent(), Weather.from(diaryRequest.getWeather()));
                        return existDiary;
                    }).toList();

            List<Diary> writtenDiaries = request.getRequests().stream()
                    .filter(diaryRequest -> !uids.contains(diaryRequest.getUid()))
                    .map(diaryRequest ->
                            Diary.write(
                                    new DiaryId(0L, UUID.fromString(diaryRequest.getUid())),
                                    diaryRequest.getContent(),
                                    new UserId(diaryRequest.getUserId()),
                                    Weather.from(diaryRequest.getWeather())
                            )
                    ).toList();

            List<Diary> savedDiaries = new ArrayList<>(updatedDiaries);
            savedDiaries.addAll(writtenDiaries);

            return diaryPort.saveAll(savedDiaries);
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_FAILED);
        }
    }
}
