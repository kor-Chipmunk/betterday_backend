package com.mashup.betterday.report.model;

import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.user.model.UserId;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class WeeklyReport {

    @NonNull private WeeklyReportId id;
    @NonNull private ReportDate reportDate;
    @NonNull private Feelings feelings;
    @NonNull private Content content;

    @NonNull private UserId userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static WeeklyReport generate(
            WeeklyReportId id,
            ReportDate reportDate,
            Feelings feelings,
            Content content,
            UserId userId
    ) {
        return new WeeklyReport(
                id,
                reportDate,
                feelings,
                content,
                userId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

}
