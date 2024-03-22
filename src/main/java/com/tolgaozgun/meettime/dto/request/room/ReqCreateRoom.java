package com.tolgaozgun.meettime.dto.request.room;


import com.tolgaozgun.meettime.entity.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqCreateRoom {

    @NotNull
    private String name;

    @NotNull
    private RoomType roomType;

    @NotBlank
    private String description;

    @NotNull
    private ReqRoomSchedule schedule;

}
