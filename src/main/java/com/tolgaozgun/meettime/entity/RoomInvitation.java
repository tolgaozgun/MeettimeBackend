package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.entity.enums.InvitationStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "room_invitations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInvitation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "invitee_id", referencedColumnName = "id", nullable = false)
    private User invitee;

    @ManyToOne
    @JoinColumn(name = "inviter_id", referencedColumnName = "id", nullable = false)
    private User inviter;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    @Nullable
    private Date acceptedAt;

}
