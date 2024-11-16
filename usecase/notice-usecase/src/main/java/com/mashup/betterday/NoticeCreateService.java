package com.mashup.betterday;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.notice.exception.NoticeValidationException;
import com.mashup.betterday.notice.model.Content;
import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.notice.model.NoticeId;
import com.mashup.betterday.notice.model.Title;
import com.mashup.port.NoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeCreateService implements NoticeCreateUsecase {

    private final NoticePort noticePort;

    @Override
    public Notice create(Request request) {
        try {
            final Notice generatedNotice = Notice.create(
                    NoticeId.empty(),
                    new Title(request.getTitle()),
                    new Content(request.getContent())
            );
            return noticePort.save(generatedNotice);
        } catch (NoticeValidationException exception) {
            throw BusinessException.from(ErrorCode.NOTICE_CREATE_FAILED);
        }
    }
}
