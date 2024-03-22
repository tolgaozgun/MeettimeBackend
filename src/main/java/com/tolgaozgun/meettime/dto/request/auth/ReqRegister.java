package com.tolgaozgun.meettime.dto.request.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReqRegister {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
