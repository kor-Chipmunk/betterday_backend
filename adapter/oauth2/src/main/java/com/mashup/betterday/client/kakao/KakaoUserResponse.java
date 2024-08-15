package com.mashup.betterday.client.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoUserResponse {

    private String id; // 1234567890

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt; // 2024-08-15T06:58:12Z

}
