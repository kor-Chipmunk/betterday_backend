package com.mashup.betterday;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.jwt.JwtService;
import com.mashup.betterday.user.model.User;
import com.mashup.port.UserPort;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService implements RefreshTokenUsecase {

    private final JwtService jwtService;

    private final UserPort userPort;

    @Override
    public TokenResponse refresh(Request request) throws BusinessException {
        validateRefreshToken(request.getRefreshToken());

        final long id = jwtService.getRefreshTokenPayload(request.getRefreshToken(), "id", Long.class);
        final String userId = jwtService.getRefreshTokenSubject(request.getRefreshToken());

        User user = userPort.findByEmail(userId);
        if (user.getId().getValue() != id) {
            throw BusinessException.from(ErrorCode.USER_NOT_FOUND);
        }

        String newAccessToken = jwtService.generateAccessToken(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                new Date()
        );

        return new TokenResponse(newAccessToken);
    }

    private void validateRefreshToken(String refreshToken) {
        if (refreshToken == null) {
            throw BusinessException.from(ErrorCode.REFRESH_TOKEN_CREATE_FAILED);
        }

        boolean isValid = jwtService.verifyRefreshToken(refreshToken);
        if (!isValid) {
            throw BusinessException.from(ErrorCode.REFRESH_TOKEN_CREATE_FAILED);
        }
    }
}
