package com.mashup.betterday.sns.publisher;

import com.mashup.betterday.event.payload.DiaryCreatedEventPayload;
import com.mashup.betterday.sns.config.properties.AwsSnsTopicProperties;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsSnsTopicProperties.class)
@Service
public class DiarySnsPublisher {

    private final SnsTemplate snsTemplate;
    private final AwsSnsTopicProperties properties;

    public void publish(DiaryCreatedEventPayload payload) {
        log.info("topic : {}", properties.getTopicArn());
        snsTemplate.convertAndSend(properties.getTopicArn(), payload);
        log.info("Successfully published message to topic ARN: {}", properties.getTopicArn());
    }

}
