package com.mashup.betterday;

import com.mashup.betterday.notice.model.Notice;

public interface NoticeDeleteUsecase {
    Notice delete(Long id);
}
