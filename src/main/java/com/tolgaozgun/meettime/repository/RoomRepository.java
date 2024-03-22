package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.Room;
import com.tolgaozgun.meettime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(int id);

    Optional<Room> findByLink(String link);

    List<Room> findAllByOwner(User owner);

    List<Room> findAllByModeratorsContaining(User moderator);

    boolean existsByLink(String link);


}
