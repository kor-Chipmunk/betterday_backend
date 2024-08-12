package com.mashup.betterday.weeklyreport;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.WeeklyReportPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class WeeklyReportAdapter implements WeeklyReportPort {

    private final WeeklyReportJpaRepository weeklyReportJpaRepository;

    @Override
    @Transactional
    public WeeklyReport save(WeeklyReport weeklyReport) {
        WeeklyReportEntity weeklyReportEntity = weeklyReportJpaRepository.save(
                WeeklyReportEntityConverter.toEntity(weeklyReport));
        return WeeklyReportEntityConverter.toModel(weeklyReportEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeeklyReport> findByUserId(UserId userId) {
        List<WeeklyReport> weeklyReportEntities = weeklyReportJpaRepository.findByUserIdOrderByYearsDescWeekDesc(
                        userId.getValue()).stream()
                .map(WeeklyReportEntityConverter::toModel)
                .toList();
        return weeklyReportEntities;
    }

    @Override
    @Transactional(readOnly = true)
    public WeeklyReport findByUserIdAndReportDate(UserId userId, ReportDate reportDate) {
        WeeklyReportEntity weeklyReportEntity = weeklyReportJpaRepository.findByUserIdAndYearsAndWeek(
                userId.getValue(),
                reportDate.getYear(),
                reportDate.getWeek()
        ).orElseThrow(() -> BusinessException.from(ErrorCode.WEEKLY_REPORT_NOT_FOUND));
        return WeeklyReportEntityConverter.toModel(weeklyReportEntity);
    }
}
