package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.ChangeEmailCode;
import com.tolgaozgun.meettime.entity.enums.ChangeEmailCodeType;
import com.tolgaozgun.meettime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChangeEmailCodeRepository extends JpaRepository<ChangeEmailCode, Long> {

    List<ChangeEmailCode> findByUser(User user);

    List<ChangeEmailCode> findByUserAndValid(User user, Boolean valid);

    List<ChangeEmailCode> findByUserAndValidAndUsed(User user, Boolean valid, Boolean used);

    Optional<ChangeEmailCode> findByUserAndValidAndUsedAndChangeEmailCodeType(User user,
                                                                              Boolean valid,
                                                                              Boolean used,
                                                                              ChangeEmailCodeType type);

    void deleteByUser(User user);


}
