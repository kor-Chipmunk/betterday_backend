package com.mashup.betterday;

import com.mashup.betterday.notice.model.Notice;
import lombok.Data;

public interface NoticeCreateUsecase {
    Notice create(Request request);

    @Data
    class Request {
        private final String title;
        private final String content;
    }
}
