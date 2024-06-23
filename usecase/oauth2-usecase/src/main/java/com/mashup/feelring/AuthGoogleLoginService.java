package com.mashup.feelring;

import com.mashup.feelring.jwt.JwtService;
import com.mashup.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthGoogleLoginService implements AuthGoogleLoginUsecase {

    private final UserPort userPort;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(Request request) {
//        User user = userPort.findByEmail(request.getEmail());
//
//        String accessToken = jwtService.generateAccessToken(
//                user.getId().getValue(),
//                user.getAccount().getEmail(),
//                new Date()
//        );
//
//        String refreshToken = jwtService.generateRefreshToken(new Date());

        return null;
    }
}
