package com.mashup.betterday.model.user;

import com.mashup.betterday.user.model.User;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDto {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String image;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static UserDto from(User user) {
        return new UserDto(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                user.getProfile().getNickname(),
                user.getProfile().getImage().getLink(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
}
