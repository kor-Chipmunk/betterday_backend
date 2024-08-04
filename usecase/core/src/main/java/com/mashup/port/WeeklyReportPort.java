package com.mashup.port;

import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.User;
import com.mashup.betterday.user.model.UserId;
import java.util.List;

public interface WeeklyReportPort {
    WeeklyReport save(WeeklyReport weeklyReport);
    List<WeeklyReport> findByUserId(UserId userId);
    WeeklyReport findByUserIdAndReportDate(UserId userId, ReportDate reportDate);
}
