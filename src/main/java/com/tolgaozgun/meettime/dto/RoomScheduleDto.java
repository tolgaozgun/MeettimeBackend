package com.tolgaozgun.meettime.dto;

import com.tolgaozgun.meettime.entity.enums.ScheduleType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.TimeZone;

@Data
@Builder
public class RoomScheduleDto {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

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
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private TimeZone timeZone;

}
