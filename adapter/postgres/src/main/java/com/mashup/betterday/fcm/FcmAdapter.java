package com.mashup.betterday.fcm;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.fcm.model.FCMId;
import com.mashup.port.FcmPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FcmAdapter implements FcmPort {
    private final FcmJpaRepository fcmJpaRepository;

    @Override
    @Transactional
    public FCM save(FCM fcm) {
        FcmEntity userEntity = fcmJpaRepository.save(
                FcmEntityConverter.toEntity(fcm));
        return FcmEntityConverter.toModel(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public FCM findById(FCMId id) {
        FcmEntity fcmEntity = fcmJpaRepository.findById(id.getValue())
                .orElseThrow(() -> BusinessException.from(ErrorCode.FCM_NOT_FOUND));
        return FcmEntityConverter.toModel(fcmEntity);
    }
}
