package com.tolgaozgun.meettime.dto.request.room;

import com.tolgaozgun.meettime.entity.enums.ScheduleType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ReqRoomSchedule {

    private ScheduleType roomType;

    @NotNull
    @Min(value=5)
    @Max(value=120)
    private int minuteIntervals = 15;

    @NotNull
    @Min(value=1)
    @Max(value=23)
    private int notBeforeHour = 9;

    @NotNull
    @Min(value=1)
    @Max(value=24)
    private int notAfterHour = 17;

    @Nullable
    private String startDate;

    @Nullable
    private String endDate;

    @NotNull
    private String timeZone;

}
