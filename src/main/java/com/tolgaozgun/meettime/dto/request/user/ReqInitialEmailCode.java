package com.tolgaozgun.meettime.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqInitialEmailCode {

    @NotNull
    private int code;

}
