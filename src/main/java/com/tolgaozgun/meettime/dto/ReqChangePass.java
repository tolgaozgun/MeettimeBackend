package com.tolgaozgun.meettime.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReqChangePass {

    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
}
