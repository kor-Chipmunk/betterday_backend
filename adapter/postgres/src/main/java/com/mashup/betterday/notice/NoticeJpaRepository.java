package com.mashup.betterday.notice;

import com.mashup.betterday.notice.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeJpaRepository extends JpaRepository<NoticeEntity, Long> {
    Page<NoticeEntity> findByStatusOrderByCreatedAtDesc(Status status, Pageable pageable);
}
