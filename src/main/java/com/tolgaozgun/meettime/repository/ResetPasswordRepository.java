package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.ResetPasswordCode;
import com.tolgaozgun.meettime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPasswordCode, Long> {

    Optional<ResetPasswordCode> findByUser(User user);

    void deleteByUser(User user);


}
