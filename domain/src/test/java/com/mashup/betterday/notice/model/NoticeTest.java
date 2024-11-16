package com.mashup.betterday.notice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoticeTest {

    @Test
    @DisplayName("공지사항을 생성합니다.")
    void shouldCreate() {
        // given
        Title title = Title.of("제목");
        Content content = Content.of("내용");

        // when
        Notice notice = Notice.create(NoticeId.empty(), title, content);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(title, notice.getTitle()),
                () -> Assertions.assertEquals(content, notice.getContent()),
                () -> Assertions.assertEquals(Status.ACTIVE, notice.getStatus()),
                () -> Assertions.assertNotNull(notice.getCreatedAt()),
                () -> Assertions.assertNotNull(notice.getUpdatedAt()),
                () -> Assertions.assertNull(notice.getDeletedAt())
        );
    }

    @Test
    @DisplayName("공지사항을 삭제합니다.")
    void shouldInactiveWhenNoticeDeleted() {
        // given
        Title title = Title.of("제목");
        Content content = Content.of("내용");
        Notice notice = Notice.create(NoticeId.empty(), title, content);

        // when
        notice.delete();

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(Status.INACTIVE, notice.getStatus()),
                () -> Assertions.assertNotNull(notice.getDeletedAt())
        );
    }

}
