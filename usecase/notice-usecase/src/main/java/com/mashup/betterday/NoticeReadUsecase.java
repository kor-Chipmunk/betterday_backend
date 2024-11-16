package com.mashup.betterday;

import com.mashup.betterday.notice.model.Notice;
import java.util.List;

public interface NoticeReadUsecase {
    List<Notice> read(int page, int size);

    Notice read(Long id);
}
