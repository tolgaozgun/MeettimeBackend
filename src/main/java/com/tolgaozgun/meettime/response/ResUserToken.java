package com.tolgaozgun.meettime.response;

import com.tolgaozgun.meettime.dto.UserDto;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ResUserToken {

    private UserDto user;
    private String accessToken;
    private String refreshToken;

}
