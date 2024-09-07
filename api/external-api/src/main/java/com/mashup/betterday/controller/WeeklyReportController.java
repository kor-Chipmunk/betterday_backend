package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.WeeklyReportCreateUsecase;
import com.mashup.betterday.WeeklyReportCreateUsecase.Request;
import com.mashup.betterday.WeeklyReportReadUsecase;
import com.mashup.betterday.model.weeklyreport.WeeklyReportCreateRequest;
import com.mashup.betterday.model.weeklyreport.WeeklyReportDto;
import com.mashup.betterday.report.model.ReportDate;
import com.mashup.betterday.report.model.WeeklyReport;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주간 리포트", description = "주간 리포트 API")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/weekly-reports")
public class WeeklyReportController {

    private final WeeklyReportCreateUsecase weeklyReportCreateUsecase;
    private final WeeklyReportReadUsecase weeklyReportReadUsecase;

    @Operation(summary = "주간 리포트 생성", description = "년과 월에 해당하는 감정 목록과 설명을 포함한 주간 리포트를 생성합니다.")
    @PostMapping
    ResponseEntity<WeeklyReportDto> create(
            @RequestBody WeeklyReportCreateRequest request,
            @AuthUser User user
    ) {
        WeeklyReport createdWeeklyReport = weeklyReportCreateUsecase.create(
                new Request(
                        request.getYear(),
                        request.getWeek(),
                        request.getFeelings(),
                        request.getContent(),
                        user
                )
        );
        return ResponseEntity.ok(WeeklyReportDto.from(createdWeeklyReport));
    }

    @Operation(summary = "주간 리포트 조회", description = "모든 주간 리포트 목록을 조회합니다.")
    @GetMapping
    ResponseEntity<List<WeeklyReportDto>> read(
            @AuthUser User user
    ) {
        List<WeeklyReport> weeklyReports = weeklyReportReadUsecase.read(user);
        return ResponseEntity.ok(weeklyReports.stream()
                .map(WeeklyReportDto::from)
                .toList()
        );
    }

    @Operation(summary = "주간 리포트 조회", description = "년과 주차에 해당하는 주간 리포트를 조회합니다.")
    @GetMapping("/by-report-date")
    ResponseEntity<WeeklyReportDto> read(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "week", required = false) Integer week,
            @AuthUser User user
    ) {
        WeeklyReport weeklyReport = weeklyReportReadUsecase.read(user, new ReportDate(year, week));
        return ResponseEntity.ok(WeeklyReportDto.from(weeklyReport));
    }

}
