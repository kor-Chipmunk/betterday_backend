package com.mashup.betterday;

import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import com.mashup.port.NoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeDeleteService implements NoticeDeleteUsecase {

    private final NoticePort noticePort;

    @Override
    public Notice delete(Long id) {
        final Notice notice = noticePort.findById(new NoticeId(id));
        return noticePort.delete(notice);
    }

}
