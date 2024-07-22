package com.mashup.betterday.alarm;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmJpaRepository extends JpaRepository<AlarmEntity, Long> {
    Optional<AlarmEntity> findByDiaryId(Long diaryId);
    boolean existsByDiaryId(Long diaryId);
}
