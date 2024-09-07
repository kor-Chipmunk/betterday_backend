package com.mashup.betterday.controller;

import com.mashup.betterday.AuthUser;
import com.mashup.betterday.UserCreateUsecase;
import com.mashup.betterday.UserDeleteUsecase;
import com.mashup.betterday.UserUpdateUsecase;
import com.mashup.betterday.model.user.UserCreateRequest;
import com.mashup.betterday.model.user.UserDto;
import com.mashup.betterday.model.user.UserUpdateRequest;
import com.mashup.betterday.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저", description = "유저 API")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCreateUsecase userCreateUsecase;
    private final UserUpdateUsecase userUpdateUsecase;
    private final UserDeleteUsecase userDeleteUsecase;

    @Operation(summary = "유저 조회", description = "유저 정보를 조회합니다.")
    @GetMapping
    ResponseEntity<UserDto> read(@AuthUser User user) {
        return ResponseEntity.ok(UserDto.from(user));
    }

    @Operation(summary = "유저 생성", description = "유저를 생성합니다.")
    @PostMapping
    ResponseEntity<UserDto> create(
            @RequestBody UserCreateRequest request
    ) {
        User createdUser = userCreateUsecase.create(new UserCreateUsecase.Request(
                request.getEmail(),
                request.getPassword(),
                request.getProviderType(),
                request.getProviderId()
        ));
        return ResponseEntity.ok(UserDto.from(createdUser));
    }

    @Operation(summary = "유저 수정", description = "유저 정보를 수정합니다.")
    @PutMapping
    ResponseEntity<UserDto> update(
            @RequestBody UserUpdateRequest request,
            @AuthUser User user
    ) {
        User updatedUser = userUpdateUsecase.update(new UserUpdateUsecase.Request(
                user.getId().getValue(),
                request.getNickname(),
                request.getImage()
        ));
        return ResponseEntity.ok(UserDto.from(updatedUser));
    }

    @Operation(summary = "유저 삭제", description = "유저를 삭제합니다.")
    @DeleteMapping
    ResponseEntity<UserDto> delete(@AuthUser User user) {
        User deletedUser = userDeleteUsecase.delete(new UserDeleteUsecase.Request(
                user.getId().getValue()
        ));
        return ResponseEntity.ok(UserDto.from(deletedUser));
    }

}
