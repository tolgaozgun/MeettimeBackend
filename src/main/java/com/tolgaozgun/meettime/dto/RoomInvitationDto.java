package com.tolgaozgun.meettime.dto;

import com.tolgaozgun.meettime.entity.enums.InvitationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RoomInvitationDto {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private RoomDto room;

    @NotNull
    private UserDto invitee;

    @NotNull
    private UserDto inviter;

    @NotNull
    private Date acceptedAt;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    public String toString() {
        return "RoomInvitationDto(id=" + this.getId() + ", room=" + this.getRoom() + ", invitee=" + this.getInvitee() + ", inviter=" + this.getInviter() + ", acceptedAt=" + this.getAcceptedAt() + ", invitationStatus=" + this.getStatus() + ")";
    }

}
