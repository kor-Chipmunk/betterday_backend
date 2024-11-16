package com.mashup.betterday;

import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import com.mashup.port.NoticePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeReadService implements NoticeReadUsecase {

    private final NoticePort noticePort;

    @Override
    public List<Notice> read(int page, int size) {
        return noticePort.findAll(page, size);
    }

    @Override
    public Notice read(Long id) {
        return noticePort.findById(new NoticeId(id));
    }

}
