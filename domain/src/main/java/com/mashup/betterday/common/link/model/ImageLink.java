package com.mashup.betterday.common.link.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ImageLink extends Link {

    public ImageLink(String link) {
        super(link);
    }

}
