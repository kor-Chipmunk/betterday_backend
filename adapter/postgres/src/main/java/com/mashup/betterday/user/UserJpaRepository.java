package com.mashup.betterday.user;

import com.mashup.betterday.user.model.ProviderType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByProviderTypeAndProviderId(ProviderType providerType, String providerId);
}
