package com.mashup.betterday.model.notice;

import com.mashup.betterday.notice.model.Notice;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class NoticeDto {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public static NoticeDto from(Notice notice) {
        return new NoticeDto(
                notice.getId().getValue(),
                notice.getTitle().getValue(),
                notice.getContent().getValue(),
                notice.getCreatedAt()
        );
    }
}
