package com.mashup.betterday.sns.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cloud.aws.sns.topic")
public class AwsSnsTopicProperties {

    private String diaryTopicArn;

}
