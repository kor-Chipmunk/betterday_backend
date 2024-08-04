package com.mashup.betterday;

import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.User;
import com.mashup.port.WeeklyReportPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeeklyReportReadService implements WeeklyReportReadUsecase {

    private final WeeklyReportPort weeklyReportPort;

    @Override
    public List<WeeklyReport> read(User user) {
        return weeklyReportPort.findByUserId(user.getId());
    }

    @Override
    public WeeklyReport read(User user, ReportDate reportDate) {
        return weeklyReportPort.findByUserIdAndReportDate(user.getId(), reportDate);
    }

}
