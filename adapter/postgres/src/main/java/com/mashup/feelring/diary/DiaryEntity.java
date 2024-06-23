package com.mashup.feelring.diary;

import com.mashup.feelring.diary.converter.ContentConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "diary")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    @Convert(converter = ContentConverter.class)
    private String content;
    private Long userId;
    private String category;
    private String alarmUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
