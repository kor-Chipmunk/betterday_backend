package com.mashup.betterday.model.weeklyreport;

import com.mashup.betterday.report.model.WeeklyReport;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class WeeklyReportDto {
    private final Long id;
    private final Integer year;
    private final Integer week;
    private final List<String> feelings;
    private final String content;
    private final LocalDateTime createdAt;

    public static WeeklyReportDto from(WeeklyReport weeklyReport) {
        return new WeeklyReportDto(
                weeklyReport.getId().getValue(),
                weeklyReport.getReportDate().getYear(),
                weeklyReport.getReportDate().getWeek(),
                weeklyReport.getFeelings().getFeelingList().stream().map(Enum::name).toList(),
                weeklyReport.getContent().getContent(),
                weeklyReport.getCreatedAt()
        );
    }
}
