package com.tolgaozgun.meettime.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqChangeEmail {

    @NotNull
    private String newEmail;

}
