package com.mashup.betterday.oauth2.twitter;

import com.mashup.betterday.model.OAuthToken;
import com.mashup.betterday.model.OAuthUser;
import com.mashup.betterday.oauth2.OAuthClient;

public class TwitterClient implements OAuthClient {
    @Override
    public OAuthToken requestAccessToken(String code) {
        return null;
    }

    @Override
    public OAuthToken renewAccessToken(OAuthToken oAuthToken) {
        return null;
    }

    @Override
    public OAuthUser requestUserInfo(OAuthToken oAuthToken) {
        return null;
    }
}
