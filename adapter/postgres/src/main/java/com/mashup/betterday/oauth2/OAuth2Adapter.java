package com.mashup.betterday.oauth2;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.UserEntity;
import com.mashup.betterday.user.UserEntityConverter;
import com.mashup.betterday.user.UserJpaRepository;
import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.User;
import com.mashup.port.OAuth2Port;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2Adapter implements OAuth2Port {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByProvider(Provider provider) {

        log.info("providerType: {}, Id: {}", provider.getType(), provider.getId());

        UserEntity userEntity = userJpaRepository.findByProviderTypeAndProviderId(
                        provider.getType(),
                        provider.getId()
                )
                .orElseThrow(() -> BusinessException.from(ErrorCode.USER_NOT_FOUND));
        return UserEntityConverter.toModel(userEntity);
    }
}
