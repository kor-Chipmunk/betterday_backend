package com.mashup.betterday;

import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.report.exception.WeeklyReportValidationException;
import com.mashup.betterday.report.model.Feeling;
import com.mashup.betterday.report.model.Feelings;
import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.report.model.WeeklyReportId;
import com.mashup.port.WeeklyReportPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeeklyReportCreateService implements WeeklyReportCreateUsecase {

    private final WeeklyReportPort weeklyReportPort;

    @Override
    public WeeklyReport create(Request request) {
        try {
            List<Feeling> mappedFeelings = request.getFeelings().stream().map(Feeling::from).toList();

            WeeklyReport generatedWeeklyReport = WeeklyReport.generate(
                    WeeklyReportId.empty(),
                    new ReportDate(request.getYear(), request.getWeek()),
                    new Feelings(mappedFeelings),
                    new Content(request.getContent()),
                    request.getUser().getId()
            );

            return weeklyReportPort.save(generatedWeeklyReport);
        } catch (WeeklyReportValidationException exception) {
            throw BusinessException.from(ErrorCode.WEEKLY_REPORT_CREATE_FAILED);
        }
    }
}
