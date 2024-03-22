package com.tolgaozgun.meettime.dto.request.user;


import lombok.Data;

@Data
public class ReqResetPassVerifyCode {

    private String email;
    private String verifyCode;
}
