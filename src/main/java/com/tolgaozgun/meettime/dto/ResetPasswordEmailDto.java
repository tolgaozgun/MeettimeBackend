package com.tolgaozgun.meettime.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordEmailDto {

    private String name;
    private String email;
    private int code;

}
