package com.mashup.betterday.diary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryJpaRepository extends JpaRepository<DiaryEntity, Long> {
    Optional<DiaryEntity> findByUserIdAndUid(Long userId, String uid);

    Optional<DiaryEntity> findByUid(String uid);

    Page<DiaryEntity> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);

    List<DiaryEntity> findByUidIn(List<String> uid);

    List<DiaryEntity> findByUserIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(Long userId, LocalDateTime from,
                                                                                   LocalDateTime until);

    List<DiaryEntity> findAllByUserId(Long userId);
}
