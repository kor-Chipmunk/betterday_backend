package com.mashup.feelring.user;

import com.mashup.feelring.user.model.Account;
import com.mashup.feelring.user.model.Profile;
import com.mashup.feelring.user.model.Provider;
import com.mashup.feelring.user.model.Role;
import com.mashup.feelring.user.model.User;
import com.mashup.feelring.user.model.UserId;

public class UserEntityConverter {

    private UserEntityConverter() {}

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                user.getAccount().getPassword(),
                user.getRole().name(),
                user.getProfile().getNickname(),
                user.getProfile().getImage(),
                user.getProvider().getType(),
                user.getProvider().getId(),
                null,
                user.getLastLoginAt(),
                user.getLastLogoutAt(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    public static User toModel(UserEntity userEntity) {
        return new User(
                new UserId(userEntity.getId()),
                new Account(
                    userEntity.getEmail(),
                    userEntity.getPassword()
                ),
                Role.from(userEntity.getRole()),
                new Profile(
                        userEntity.getNickname(),
                        userEntity.getImage()
                ),
                new Provider(
                        userEntity.getProviderName(),
                        userEntity.getProviderId()
                ),
                userEntity.getLastLoginAt(),
                userEntity.getLastLogoutAt(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt()
        );
    }

}
