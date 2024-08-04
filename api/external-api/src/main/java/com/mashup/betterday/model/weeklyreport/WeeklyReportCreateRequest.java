package com.mashup.betterday.model.weeklyreport;

import java.util.List;
import lombok.Data;

@Data
public class WeeklyReportCreateRequest {
    private Integer year;
    private Integer week;
    private List<String> feelings;
    private String content;
}
