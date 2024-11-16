package com.mashup.betterday.notice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Notice {

    private NoticeId id;

    private Title title;
    private Content content;
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Notice create(
            NoticeId id,
            Title title,
            Content content
    ) {
        return new Notice(
                id,
                title,
                content,
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public void delete() {
        this.status = Status.INACTIVE;
        this.deletedAt = LocalDateTime.now();
    }

}
