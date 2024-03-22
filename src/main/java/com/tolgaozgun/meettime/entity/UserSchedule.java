package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.annotations.ValidRoomDates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TimeZone;


@Entity
@Table(name = "user_schedules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidRoomDates
public class UserSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_schedule_id")
    private List<UserScheduleTimes> dates;

    @NotNull
    private String roomLink;

    @NotNull
    private TimeZone timeZone;

}