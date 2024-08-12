package com.mashup.betterday.user;

import com.mashup.betterday.user.model.Account;
import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.User;
import com.mashup.betterday.user.model.UserId;

public class UserEntityConverter {

    private UserEntityConverter() {
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                user.getAccount().getPassword(),
                user.getRole(),
                user.getProvider().getType(),
                user.getProvider().getId(),
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
                userEntity.getRole(),
                new Provider(
                        userEntity.getProviderType(),
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
