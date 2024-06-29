package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface UserDeleteUsecase {
    User delete(Request request);

    @Data
    class Request {
        private final Long id;
    }
}
