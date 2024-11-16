package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.NoticeCreateUsecase;
import com.mashup.betterday.NoticeCreateUsecase.Request;
import com.mashup.betterday.NoticeDeleteUsecase;
import com.mashup.betterday.NoticeReadUsecase;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.model.notice.NoticeCreateRequest;
import com.mashup.betterday.model.notice.NoticeDto;
import com.mashup.betterday.notice.model.Notice;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지사항", description = "공지사항 API")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeCreateUsecase noticeCreateUsecase;
    private final NoticeReadUsecase noticeReadUsecase;
    private final NoticeDeleteUsecase noticeDeleteUsecase;

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.")
    @PostMapping
    ResponseEntity<NoticeDto> create(
            @RequestBody NoticeCreateRequest request,
            @AuthUser User user
    ) {
        try {
            user.getRole().checkAdmin();
        } catch (Exception e) {
            throw BusinessException.from(ErrorCode.ADMIN_VALIDATION_FAILED);
        }

        Notice createdNotice = noticeCreateUsecase.create(
                new Request(
                        request.getTitle(),
                        request.getContent()
                )
        );
        return ResponseEntity.ok(NoticeDto.from(createdNotice));
    }

    @Operation(summary = "공지사항 조회", description = "공지사항을 페이징 조회합니다.")
    @GetMapping
    ResponseEntity<List<NoticeDto>> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Notice> notices = noticeReadUsecase.read(page, size);
        return ResponseEntity.ok(notices.stream()
                .map(NoticeDto::from)
                .toList()
        );
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    @DeleteMapping("/{id}")
    ResponseEntity<NoticeDto> delete(
            @PathVariable Long id,
            @AuthUser User user
    ) {
        try {
            user.getRole().checkAdmin();
        } catch (Exception e) {
            throw BusinessException.from(ErrorCode.ADMIN_VALIDATION_FAILED);
        }

        Notice deletedNotice = noticeDeleteUsecase.delete(id);
        return ResponseEntity.ok(NoticeDto.from(deletedNotice));
    }

}
