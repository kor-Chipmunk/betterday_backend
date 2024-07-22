package com.mashup.betterday.diary;

import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.DiaryPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DiaryAdapter implements DiaryPort {

    private final DiaryJpaRepository diaryJpaRepository;

    @Override
    @Transactional
    public Diary save(final Diary diary) {
        final DiaryEntity diaryEntity = diaryJpaRepository.save(DiaryEntityConverter.toEntity(diary));
        return DiaryEntityConverter.toModel(diaryEntity);
    }

    @Override
    @Transactional
    public List<Diary> saveAll(final List<Diary> diaries) {
        final List<DiaryEntity> diaryEntities = diaries.stream().map(DiaryEntityConverter::toEntity).toList();
        final List<DiaryEntity> savedDiaryEntities = diaryJpaRepository.saveAll(diaryEntities);
        final List<Diary> savedDiaries = savedDiaryEntities.stream().map(DiaryEntityConverter::toModel).toList();
        return savedDiaries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diary> findAllByUid(final UserId userId, final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<DiaryEntity> diaryEntities = diaryJpaRepository.findByUserIdOrderByIdDesc(
                userId.getValue(),
                pageRequest
        );
        return diaryEntities.getContent().stream()
                .map(DiaryEntityConverter::toModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Diary findByUid(final DiaryId id) {
        final DiaryEntity diaryEntity = diaryJpaRepository.findByUid(id.getUid().toString())
                .orElse(null);
        if (diaryEntity == null) throw BusinessException.from(ErrorCode.DIARY_NOT_FOUND);
        return DiaryEntityConverter.toModel(diaryEntity);
    }

    @Override
    @Transactional
    public Diary delete(final Diary diary) {
        final DiaryEntity diaryEntity = diaryJpaRepository.findByUid(
                diary.getId().getUid().toString()
        ).orElse(null);
        if (diaryEntity == null) throw BusinessException.from(ErrorCode.DIARY_NOT_FOUND);
        diaryJpaRepository.delete(diaryEntity);
        return DiaryEntityConverter.toModel(diaryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diary> findAllByUid(final List<DiaryId> uids) {
        final List<DiaryEntity> diaryEntities = diaryJpaRepository.findByUidIn(
                uids.stream()
                        .map(DiaryId::getUid)
                        .map(UUID::toString)
                        .toList()
        );
        return diaryEntities.stream()
                .map(DiaryEntityConverter::toModel)
                .toList();
    }
}
