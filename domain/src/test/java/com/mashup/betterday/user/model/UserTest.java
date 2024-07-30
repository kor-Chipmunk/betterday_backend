package com.mashup.betterday.user.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import com.mashup.betterday.common.link.model.ImageLink;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class UserTest {

    @DisplayName("회원가입")
    @Nested
    class SignupUsecase {
        @DisplayName("유저는 회원가입합니다.")
        @Test
        void shouldSignup() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                User signUpUser = User.signUp(
                        new UserId(1L),
                        new Account(
                                "test@naver.com",
                                "123456"
                        ),
                        Role.USER,
                        new Provider(
                                ProviderType.GOOGLE,
                                "googleId1"
                        )
                );

                assertAll("signUpUser",
                        () -> assertEquals(new UserId(1L), signUpUser.getId()),
                        () -> assertEquals("test@naver.com", signUpUser.getAccount().getEmail()),
                        () -> assertEquals("123456", signUpUser.getAccount().getPassword()),
                        () -> assertEquals(Role.USER, signUpUser.getRole()),
                        () -> assertEquals(mockNow, signUpUser.getCreatedAt()),
                        () -> assertEquals(mockNow, signUpUser.getUpdatedAt())
                );
            }
        }
    }

    @DisplayName("로그인")
    @Nested
    class LoginUsecase {
        @DisplayName("유저는 로그인합니다.")
        @Test
        void shouldLogin() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                User signInUser = User.signUp(
                        new UserId(1L),
                        new Account(
                                "test@naver.com",
                                "123456"
                        ),
                        Role.USER,
                        new Provider(
                                ProviderType.GOOGLE,
                                "googleId1"
                        )
                );

                signInUser.signIn();

                assertAll("signInUser",
                        () -> assertEquals(mockNow, signInUser.getLastLoginAt()),
                        () -> assertEquals(mockNow, signInUser.getUpdatedAt())
                );
            }
        }
    }

    @DisplayName("로그아웃")
    @Nested
    class LogoutUsecase {
        @DisplayName("로그아웃합니다.")
        @Test
        void shouldLogout() {
            LocalDateTime mockNow = LocalDateTime.of(2024, 5, 27, 0, 0, 0);

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                User signOutUser = User.signUp(
                        new UserId(1L),
                        new Account(
                                "test@naver.com",
                                "123456"
                        ),
                        Role.USER,
                        new Provider(
                                ProviderType.GOOGLE,
                                "googleId1"
                        )
                );

                signOutUser.signOut();

                assertAll("signInUser",
                        () -> assertEquals(mockNow, signOutUser.getLastLogoutAt()),
                        () -> assertEquals(mockNow, signOutUser.getUpdatedAt())
                );
            }
        }
    }

    @DisplayName("탈퇴")
    @Nested
    class ResignUsecase {
        @DisplayName("탈퇴합니다.")
        @Test
        void shouldResign() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                User resignUser = User.signUp(
                        new UserId(1L),
                        new Account(
                                "test@naver.com",
                                "123456"
                        ),
                        Role.USER,
                        new Provider(
                                ProviderType.GOOGLE,
                                "googleId1"
                        )
                );

                resignUser.resign();

                assertAll("signInUser",
                        () -> assertEquals(Role.DELETED, resignUser.getRole()),
                        () -> assertEquals(mockNow, resignUser.getDeletedAt()),
                        () -> assertEquals(mockNow, resignUser.getUpdatedAt())
                );
            }
        }
    }


}
