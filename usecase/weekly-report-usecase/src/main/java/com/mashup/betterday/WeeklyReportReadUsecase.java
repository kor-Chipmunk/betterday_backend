package com.mashup.betterday;

import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.User;
import java.util.List;

public interface WeeklyReportReadUsecase {
    List<WeeklyReport> read(User user);
    WeeklyReport read(User user, ReportDate reportDate);
}
