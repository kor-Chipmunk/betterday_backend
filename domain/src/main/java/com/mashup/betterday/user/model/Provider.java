package com.mashup.betterday.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Provider {

    private ProviderType type;
    private String id;

}
