package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.ResetPasswordCode;
import com.tolgaozgun.meettime.entity.Room;
import com.tolgaozgun.meettime.entity.RoomInvitation;
import com.tolgaozgun.meettime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomInvitationRepository extends JpaRepository<RoomInvitation, Long> {

    List<RoomInvitation> findAllByRoom(Room room);

}
