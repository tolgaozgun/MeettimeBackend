package com.tolgaozgun.meettime.dto;


import lombok.Data;

@Data
public class ReqResetPassVerifyCode {

    private String email;
    private String verifyCode;
}
