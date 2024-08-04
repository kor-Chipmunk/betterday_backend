package com.mashup.betterday.report.model;

import com.mashup.betterday.report.exception.WeeklyReportValidationException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ReportDate {

    private int year;
    private int week;

    public ReportDate(int year, int week) {
        validateDate(year, week);

        this.year = year;
        this.week = week;
    }

    private void validateDate(int year, int week) {
        if (year < 1) {
            throw new WeeklyReportValidationException("년도는 양수여야 합니다.");
        }

        if (week < 1 || week > 53) {
            throw new WeeklyReportValidationException("주는 1과 53 사이여야 합니다.");
        }
    }

    public static ReportDate empty() {
        return new ReportDate(1970, 1);
    }

}
