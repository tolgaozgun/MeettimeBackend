package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.annotations.ValidRoomDates;
import com.tolgaozgun.meettime.entity.enums.ScheduleType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.TimeZone;


@Entity
@Table(name = "room_schedules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidRoomDates
public class RoomSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
