package com.mashup.betterday.common.link.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MusicLink extends Link {

    public MusicLink(String link) {
        super(link);
    }

}
