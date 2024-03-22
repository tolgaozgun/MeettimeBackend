package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.entity.enums.RoomType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private RoomType roomType;

    @NotBlank
    private String description;

    @Nullable
    private String link;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "room_moderators",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> moderators;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "room_participants",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;

    @NotNull
    @OneToMany(mappedBy = "room")
    private List<RoomInvitation> invitations;

    @NotNull
    @OneToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    private RoomSchedule roomSchedule;

    @NotNull
    @OneToMany
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    List<UserSchedule> userSchedules;

    public String toString(){
        return "Room [id=" + id + ", name=" + name + ", owner=" + owner + "]";
    }
}
