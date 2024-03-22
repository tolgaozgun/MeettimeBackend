package com.tolgaozgun.meettime.dto;

import com.tolgaozgun.meettime.entity.enums.RoomType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomDto {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private RoomType roomType;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Nullable
    private String link;

    @NotNull
    private UserDto owner;

    @NotNull
    private List<UserDto> moderators;

    @NotNull
    private RoomScheduleDto schedule;

    public String toString() {
        return "Room [id=" + id + ", name=" + name + ", owner=" + owner + "]";
    }

}
