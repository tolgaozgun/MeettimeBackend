package com.tolgaozgun.meettime.dto.request.user;


import lombok.Data;

@Data
public class ReqResetPassVerifyPassword {

    private String email;
    private String verifyCode;
    private String newPassword;
}
