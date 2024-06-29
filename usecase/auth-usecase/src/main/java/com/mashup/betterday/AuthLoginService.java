package com.mashup.betterday;

import com.mashup.betterday.jwt.JwtService;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.User;
import com.mashup.port.UserPort;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthLoginService implements AuthLoginUsecase {

    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(Request request) {
        User user = userPort.findByEmail(request.getEmail());
        validatePassword(user, request.getPassword());

        String accessToken = jwtService.generateAccessToken(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                new Date()
        );

        String refreshToken = jwtService.generateRefreshToken(new Date());

        return new LoginResponse(
                user,
                accessToken,
                refreshToken
        );
    }

    private void validatePassword(User user, String rawPassword) {
        boolean matches = passwordEncoder.matches(
                rawPassword,
                user.getAccount().getPassword()
        );

        if (!matches) {
            throw BusinessException.from(ErrorCode.LOGIN_FAILED);
        }
    }
}
