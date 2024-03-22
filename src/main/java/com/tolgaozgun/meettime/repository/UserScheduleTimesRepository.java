package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.User;
import com.tolgaozgun.meettime.entity.UserScheduleTimes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserScheduleTimesRepository extends JpaRepository<UserScheduleTimes, Long> {

}
