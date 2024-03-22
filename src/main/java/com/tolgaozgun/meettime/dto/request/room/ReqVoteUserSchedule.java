package com.tolgaozgun.meettime.dto.request.room;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class ReqVoteUserSchedule {

    @NotNull
    private Map<String, List<String>> dates;

    @NotNull
    private String timeZone;


}
