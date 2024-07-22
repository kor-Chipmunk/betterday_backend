package com.mashup.betterday.oauth2;

import com.mashup.betterday.model.OAuthToken;
import com.mashup.betterday.model.OAuthUser;

public interface OAuthClient {
    OAuthToken requestAccessToken(String code);

    OAuthToken renewAccessToken(OAuthToken oAuthToken);

    OAuthUser requestUserInfo(OAuthToken oAuthToken);
}
