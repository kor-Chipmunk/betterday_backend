package com.mashup.feelring.user;

import com.mashup.feelring.exception.BusinessException;
import com.mashup.feelring.exception.ErrorCode;
import com.mashup.feelring.user.model.User;
import com.mashup.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = userJpaRepository.save(UserEntityConverter.toEntity(user));
        return UserEntityConverter.toModel(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        UserEntity userEntity = userJpaRepository.findById(id)
                .orElseThrow(() -> BusinessException.from(ErrorCode.USER_NOT_FOUND));
        return UserEntityConverter.toModel(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> BusinessException.from(ErrorCode.USER_NOT_FOUND));
        return UserEntityConverter.toModel(userEntity);
    }
}
