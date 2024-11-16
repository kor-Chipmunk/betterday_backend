package com.mashup.port;

import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import java.util.List;

public interface NoticePort {
    Notice save(Notice notice);

    Notice findById(NoticeId id);

    Notice delete(Notice diary);

    List<Notice> findAll(int page, int size);
}
