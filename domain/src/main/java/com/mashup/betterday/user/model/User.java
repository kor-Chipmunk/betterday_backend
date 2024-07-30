package com.mashup.betterday.user.model;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class User {

    @NonNull private UserId id;
    @NonNull private Account account;
    @NonNull private Role role;
    @NonNull private Provider provider;

    private LocalDateTime lastLoginAt;
    private LocalDateTime lastLogoutAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static User signUp(
            UserId userId,
            Account account,
            Role role,
            Provider provider
    ) {
        return new User(
                userId,
                account,
                role,
                provider,
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public void signIn() {
        lastLoginAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void signOut() {
        lastLogoutAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void resign() {
        role = Role.DELETED;
        deletedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
