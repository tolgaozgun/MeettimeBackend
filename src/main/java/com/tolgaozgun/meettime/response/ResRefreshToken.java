package com.tolgaozgun.meettime.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResRefreshToken {
    private String accessToken;
}
