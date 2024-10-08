package com.mashup.betterday;

import com.mashup.betterday.DiarySyncBatchUsecase.Request.CreateItem;
import com.mashup.betterday.diary.exception.DiaryValidationException;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.DiaryPort;
import java.util.ArrayList;
import java.util.List;
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
            List<Diary> existDiaries = getExistDiaries(request.getRequests());

            Set<String> distinctUids = existDiaries.stream()
                    .map(diary -> diary.getId().getUid().toString())
                    .collect(Collectors.toUnmodifiableSet());
            List<Diary> updatedDiaries = request.getRequests().stream()
                    .filter(diaryRequest -> distinctUids.contains(diaryRequest.getUid()))
                    .map(diaryRequest -> {
                        Diary existDiary = existDiaries.stream().filter(diary -> diary.getId().getUid().toString().equals(diaryRequest.getUid())).findFirst()
                                .orElse(null);
                        if (existDiary == null) return null;

                        existDiary.edit(
                                new Content(diaryRequest.getContent()),
                                Weather.from(diaryRequest.getWeather())
                        );
                        return existDiary;
                    }).toList();

            List<Diary> writtenDiaries = request.getRequests().stream()
                    .filter(diaryRequest -> !distinctUids.contains(diaryRequest.getUid()))
                    .map(diaryRequest ->
                            Diary.write(
                                    DiaryId.withUid(diaryRequest.getUid()),
                                    new Content(diaryRequest.getContent()),
                                    new UserId(diaryRequest.getUserId()),
                                    Weather.from(diaryRequest.getWeather())
                            )
                    ).toList();

            List<Diary> savedDiaries = new ArrayList<>(updatedDiaries);
            savedDiaries.addAll(writtenDiaries);

            return diaryPort.saveAll(savedDiaries);
        } catch (DiaryValidationException exception) {
            throw BusinessException.from(ErrorCode.DIARY_CREATE_FAILED);
        }
    }

    private List<Diary> getExistDiaries(List<DiarySyncBatchUsecase.Request.CreateItem> items) {
        List<DiaryId> requestUids = items.stream()
                .map(CreateItem::getUid)
                .map(DiaryId::withUid)
                .toList();
        return diaryPort.findAllByUid(requestUids);
    }
}
