package com.mashup.betterday.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    UNKNOWN(500, "알 수 없는 오류입니다."),

    USER_CREATE_FAILED(400, "유저 생성에 실패했습니다."),
    USER_NOT_FOUND(400, "유저를 조회할 수 없습니다."),
    LOGIN_FAILED(400, "로그인에 실패했습니다."),

    DIARY_CREATE_FAILED(400, "일기 작성에 실패했습니다."),
    DIARY_NOT_FOUND(400, "일기를 조회할 수 없습니다."),

    ALARM_CREATE_FAILED(400, "알람 등록에 실패했습니다."),
    ALARM_NOT_FOUND(400, "알람을 조회할 수 없습니다."),
    ALARM_DUPLICATED(400, "알람이 이미 등록됐습니다."),

    WEEKLY_REPORT_CREATE_FAILED(400, "주간 리포트 등록에 실패했습니다."),
    WEEKLY_REPORT_NOT_FOUND(400, "주간 리포트를 조회할 수 없습니다."),

    REFRESH_TOKEN_CREATE_FAILED(400, "리프레시 토큰 생성에 실패했습니다."),
    ;

    private final int code;
    private final String message;
}
