package com.mashup.betterday.report.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class WeeklyReportId {

    private Long value;

    public static WeeklyReportId empty() {
        return new WeeklyReportId(0L);
    }

}
