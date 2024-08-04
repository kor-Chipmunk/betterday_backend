package com.mashup.betterday.weeklyreport;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyReportJpaRepository extends JpaRepository<WeeklyReportEntity, Long> {
    List<WeeklyReportEntity> findByUserIdOrderByYearsDescWeekDesc(Long userId);
    Optional<WeeklyReportEntity> findByUserIdAndYearsAndWeek(Long userId, int years, int week);
}
