package com.mashup.betterday.oauth2.kakao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class KakaoUser {
    private final String id;
    private final KakaoAccount kakao_account;

    @RequiredArgsConstructor
    @Data
    class KakaoAccount {
        private final String email;
        private final String profile;
    }
}
