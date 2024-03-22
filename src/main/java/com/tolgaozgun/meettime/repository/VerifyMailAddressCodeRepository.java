package com.tolgaozgun.meettime.repository;

import com.tolgaozgun.meettime.entity.ChangeEmailCode;
import com.tolgaozgun.meettime.entity.User;
import com.tolgaozgun.meettime.entity.VerifyMailAddressCode;
import com.tolgaozgun.meettime.entity.enums.ChangeEmailCodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VerifyMailAddressCodeRepository extends JpaRepository<VerifyMailAddressCode, Long> {

    List<VerifyMailAddressCode> findByUser(User user);

    List<VerifyMailAddressCode> findByUserAndValid(User user, Boolean valid);

    List<VerifyMailAddressCode> findByUserAndValidAndUsed(User user, Boolean valid, Boolean used);

    List<VerifyMailAddressCode> findByUserAndValidAndUsedAndExpireDateAfter(User user,
                                                                            Boolean valid,
                                                                            Boolean used,
                                                                            Date expireDate);



    void deleteByUser(User user);


}
