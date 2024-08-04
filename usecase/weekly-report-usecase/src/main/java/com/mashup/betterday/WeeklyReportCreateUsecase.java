package com.mashup.betterday;

import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.User;
import java.util.List;
import lombok.Data;

public interface WeeklyReportCreateUsecase {
    WeeklyReport create(Request request);

    @Data
    class Request {
        private final int year;
        private final int week;
        private final List<String> feelings;
        private final String content;
        private final User user;
    }
}
