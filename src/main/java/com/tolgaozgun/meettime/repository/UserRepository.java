package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findById(int id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);


}
