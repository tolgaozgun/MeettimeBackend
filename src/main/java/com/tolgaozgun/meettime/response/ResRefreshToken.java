package com.tolgaozgun.meettime.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResRefreshToken {
    private String accessToken;
}
