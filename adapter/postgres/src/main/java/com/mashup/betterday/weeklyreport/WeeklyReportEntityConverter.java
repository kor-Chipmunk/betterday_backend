package com.mashup.betterday.weeklyreport;

import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.report.model.Feelings;
import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.report.model.WeeklyReportId;
import com.mashup.betterday.user.model.UserId;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class WeeklyReportEntityConverter {

    public static WeeklyReportEntity toEntity(WeeklyReport weeklyReport) {
        return new WeeklyReportEntity(
                weeklyReport.getId().getValue(),
                weeklyReport.getReportDate().getYear(),
                weeklyReport.getReportDate().getWeek(),
                weeklyReport.getFeelings().getFeelingList(),
                weeklyReport.getContent().getContent(),
                weeklyReport.getUserId().getValue(),
                weeklyReport.getCreatedAt(),
                weeklyReport.getUpdatedAt(),
                weeklyReport.getDeletedAt()
        );
    }

    public static WeeklyReport toModel(WeeklyReportEntity weeklyReportEntity) {
        return new WeeklyReport(
                new WeeklyReportId(weeklyReportEntity.getId()),
                new ReportDate(
                        weeklyReportEntity.getYears(),
                        weeklyReportEntity.getWeek()
                ),
                new Feelings(weeklyReportEntity.getFeelings()),
                new Content(weeklyReportEntity.getContent()),
                new UserId(weeklyReportEntity.getUserId()),
                weeklyReportEntity.getCreatedAt(),
                weeklyReportEntity.getUpdatedAt(),
                weeklyReportEntity.getDeletedAt()
        );
    }

}
