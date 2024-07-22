package com.mashup.betterday.alarm;

import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.port.AlarmPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AlarmAdapter implements AlarmPort {

    private final AlarmJpaRepository alarmJpaRepository;

    @Override
    public Alarm save(Alarm alarm) {
        AlarmEntity alarmEntity = AlarmEntityConverter.toEntity(alarm);
        AlarmEntity savedAlarmEntity = alarmJpaRepository.save(alarmEntity);
        return AlarmEntityConverter.toModel(savedAlarmEntity);
    }

    @Override
    public Alarm findById(AlarmId id) {
        AlarmEntity alarmEntity = alarmJpaRepository.findById(id.getValue())
                .orElseThrow(() -> BusinessException.from(ErrorCode.ALARM_NOT_FOUND));
        return AlarmEntityConverter.toModel(alarmEntity);
    }

    @Override
    public Alarm findByDiary(DiaryId diaryId) {
        AlarmEntity alarmEntity = alarmJpaRepository.findByDiaryId(diaryId.getValue())
                .orElseThrow(() -> BusinessException.from(ErrorCode.ALARM_NOT_FOUND));
        return AlarmEntityConverter.toModel(alarmEntity);
    }

    @Override
    public boolean isExist(DiaryId diaryId) {
        return alarmJpaRepository.existsByDiaryId(diaryId.getValue());
    }

    @Override
    public List<Alarm> findAllByDiaries(List<DiaryId> diaryIds) {
        List<Long> ids = diaryIds.stream()
                .map(DiaryId::getValue)
                .toList();
        List<AlarmEntity> alarmEntities = alarmJpaRepository.findAllById(ids);
        return alarmEntities.stream()
                .map(AlarmEntityConverter::toModel)
                .toList();
    }
}
