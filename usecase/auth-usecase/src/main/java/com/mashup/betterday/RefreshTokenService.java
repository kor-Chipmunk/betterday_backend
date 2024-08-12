package com.mashup.betterday;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.jwt.JwtService;
import com.mashup.betterday.user.model.User;
import com.mashup.port.UserPort;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService implements RefreshTokenUsecase {

    private final JwtService jwtService;

    @Override
    public TokenResponse refresh(Request request) throws BusinessException {
        boolean isValid = jwtService.verifyRefreshToken(request.getRefreshToken());

        if (!isValid) {
            throw BusinessException.from(ErrorCode.WEEKLY_REPORT_CREATE_FAILED);
        }

        // TODO: 더 구현하기
        // 리프레쉬 토큰이 정상이면, 액세스 토큰을 발급해야 함
        // 액세스 토큰은 유저 정보가 포함되어 있음
        // 따라서 기존 리프레쉬 토큰에서도 유저 정보 넣어줘야 할듯

        // 아니면 만료된 액세스 토큰의 정보를 깔 수 있으면 굳이 리프레쉬 토큰을 안받고
        // 기존 액세스 토큰도 같이 넘겨주면 될듯

        return null;
    }
}
