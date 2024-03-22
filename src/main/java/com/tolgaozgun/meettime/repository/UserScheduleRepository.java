package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {

    Optional<UserSchedule> findByRoomLink(String roomLink);

}
