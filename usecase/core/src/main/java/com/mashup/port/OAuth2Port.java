package com.mashup.port;

import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.User;

public interface OAuth2Port {
    User findByProvider(Provider provider);
}
