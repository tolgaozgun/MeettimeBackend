package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean updatePasswordByEmail(String email, String password);

}
