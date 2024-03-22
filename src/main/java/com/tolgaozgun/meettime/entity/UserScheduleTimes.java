package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.annotations.ValidRoomDates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "user_schedule_times")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidRoomDates
public class UserScheduleTimes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "schedule_times", joinColumns = @JoinColumn(name = "schedule_entry_id"))
    @Column(name = "time")
    private List<LocalTime> times;
}
