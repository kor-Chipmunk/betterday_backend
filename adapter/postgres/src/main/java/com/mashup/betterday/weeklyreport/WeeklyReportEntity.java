package com.mashup.betterday.weeklyreport;

import com.mashup.betterday.report.model.Feeling;
import com.mashup.betterday.user.model.ProviderType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "weekly_reports")
public class WeeklyReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // `year` : 예약어이므로 피함
    @Column(nullable = false)
    private Integer years;

    @Column(nullable = false)
    private Integer week;

    @ElementCollection(targetClass = Feeling.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "weekly_report_feelings", joinColumns = @JoinColumn(name = "weekly_report_id"))
    @Column(name = "type")
    private List<Feeling> feelings;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
