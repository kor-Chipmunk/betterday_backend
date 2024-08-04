package com.mashup.betterday.report.model;

import com.mashup.betterday.report.exception.WeeklyReportValidationException;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Feelings {

    private static final int FEELING_COUNT = 7;

    private List<Feeling> feelingList;

    public Feelings(List<Feeling> feelingList) {
        validateFeelingList(feelingList);
        this.feelingList = feelingList;
    }

    private void validateFeelingList(List<Feeling> feelingList) {
        validateFeelingCount(feelingList);
    }

    private void validateFeelingCount(List<Feeling> feelingList) {
        if (feelingList.size() != FEELING_COUNT) {
            throw new WeeklyReportValidationException("감정 목록은 반드시 " + FEELING_COUNT + "개여야 합니다.");
        }
    }

    public static Feelings empty() {
        return new Feelings(List.of());
    }

}
