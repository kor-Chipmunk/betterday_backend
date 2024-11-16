package com.mashup.betterday.notice;

import com.mashup.betterday.notice.model.Content;
import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import com.mashup.betterday.notice.model.Title;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class NoticeEntityConverter {

    public static NoticeEntity toEntity(Notice notice) {
        return new NoticeEntity(
                notice.getId().getValue(),
                notice.getTitle().getValue(),
                notice.getContent().getValue(),
                notice.getStatus(),
                notice.getCreatedAt(),
                notice.getUpdatedAt(),
                notice.getDeletedAt()
        );
    }

    public static Notice toModel(NoticeEntity noticeEntity) {
        return new Notice(
                new NoticeId(noticeEntity.getId()),
                new Title(noticeEntity.getTitle()),
                new Content(noticeEntity.getContent()),
                noticeEntity.getStatus(),
                noticeEntity.getCreatedAt(),
                noticeEntity.getUpdatedAt(),
                noticeEntity.getDeletedAt()
        );
    }

}
