package com.mashup.betterday.diary;

import com.mashup.betterday.diary.converter.ContentConverter;
import com.mashup.betterday.diary.model.Weather;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "diaries")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    @Convert(converter = ContentConverter.class)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Weather category;

    @Column(nullable = false)
    private ZonedDateTime writtenAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
