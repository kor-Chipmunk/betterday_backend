package com.mashup.betterday.response;

import com.mashup.betterday.exception.ExceptionResponse;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return body;
        }

        if (body instanceof GlobalResponse) {
            return body;
        }

        if (body instanceof ExceptionResponse) {
            return body;
        }

        if (body != null && (body instanceof TreeMap<?, ?> || body.getClass().getTypeName().equals("byte[]"))) {
            return body;
        }

        return GlobalResponse.from(ResponseCode.OK, body);
    }
}
