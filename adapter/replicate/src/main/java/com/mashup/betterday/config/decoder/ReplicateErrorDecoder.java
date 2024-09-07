package com.mashup.betterday.config.decoder;

import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplicateErrorDecoder implements ErrorDecoder {

    @Override
    @SneakyThrows
    public BusinessException decode(String methodKey, Response response) {
        return BusinessException.from(ErrorCode.REPLICATE_CONNECTION_FAILED);
    }

}
