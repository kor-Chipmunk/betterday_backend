package com.mashup.betterday.oauth2;

import com.mashup.betterday.model.OAuthToken;
import com.mashup.betterday.model.OAuthUser;

public interface OAuthClient {
    default OAuthToken requestAccessToken(String code) {
        return null;
    }

    default OAuthToken renewAccessToken(OAuthToken oAuthToken) {
        return null;
    }

    default OAuthUser requestUserInfo(OAuthToken oAuthToken) {
        return null;
    }
}
