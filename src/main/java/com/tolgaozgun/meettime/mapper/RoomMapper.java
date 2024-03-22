package com.tolgaozgun.meettime.mapper;

import com.tolgaozgun.meettime.dto.RoomDto;
import com.tolgaozgun.meettime.dto.RoomInvitationDto;
import com.tolgaozgun.meettime.dto.RoomScheduleDto;
import com.tolgaozgun.meettime.entity.Room;
import com.tolgaozgun.meettime.entity.RoomInvitation;
import com.tolgaozgun.meettime.entity.RoomSchedule;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoomMapper {


    public RoomScheduleDto toRoomScheduleDto(RoomSchedule roomSchedule) {
        return RoomScheduleDto.builder()
                .id(roomSchedule.getId())
                .startDate(roomSchedule.getStartDate())
                .endDate(roomSchedule.getEndDate())
                .scheduleType(roomSchedule.getScheduleType())
                .minuteIntervals(roomSchedule.getMinuteIntervals())
                .notAfterHour(roomSchedule.getNotAfterHour())
                .notBeforeHour(roomSchedule.getNotBeforeHour())
                .timeZone(roomSchedule.getTimeZone())
                .build();
    }

    public RoomDto toRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .link(room.getLink())
                .roomType(room.getRoomType())
                .owner(UserMapper.toDTO(room.getOwner()))
                .moderators(UserMapper.toDTO(room.getModerators()))
                .schedule(toRoomScheduleDto(room.getRoomSchedule()))
                .build();
    }

    public RoomInvitationDto toRoomInvitationDto(RoomInvitation roomInvitation) {
        return RoomInvitationDto.builder()
                .id(roomInvitation.getId())
                .room(toRoomDto(roomInvitation.getRoom()))
                .invitee(UserMapper.toDTO(roomInvitation.getInvitee()))
                .inviter(UserMapper.toDTO(roomInvitation.getInviter()))
                .acceptedAt(roomInvitation.getAcceptedAt())
                .status(roomInvitation.getStatus())
                .build();
    }
}
