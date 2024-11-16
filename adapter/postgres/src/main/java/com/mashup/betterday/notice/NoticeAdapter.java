package com.mashup.betterday.notice;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import com.mashup.betterday.notice.model.Status;
import com.mashup.port.NoticePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NoticeAdapter implements NoticePort {

    private final NoticeJpaRepository repository;

    @Override
    @Transactional
    public Notice save(Notice notice) {
        final NoticeEntity noticeEntity = repository.save(
                NoticeEntityConverter.toEntity(notice)
        );
        return NoticeEntityConverter.toModel(noticeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice findById(NoticeId id) {
        final NoticeEntity noticeEntity = repository.findById(id.getValue())
                .orElseThrow(() -> BusinessException.from(ErrorCode.NOTICE_NOT_FOUND));
        return NoticeEntityConverter.toModel(noticeEntity);
    }

    @Override
    @Transactional
    public Notice delete(Notice notice) {
        repository.findById(notice.getId().getValue())
                .orElseThrow(() -> BusinessException.from(ErrorCode.NOTICE_NOT_FOUND));
        notice.delete();
        final NoticeEntity noticeEntity = repository.save(NoticeEntityConverter.toEntity(notice));
        return NoticeEntityConverter.toModel(noticeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> findAll(int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<NoticeEntity> noticeEntities = repository.findByStatusOrderByCreatedAtDesc(
                Status.ACTIVE,
                pageRequest
        );
        return noticeEntities.getContent().stream()
                .map(NoticeEntityConverter::toModel)
                .toList();
    }

}
