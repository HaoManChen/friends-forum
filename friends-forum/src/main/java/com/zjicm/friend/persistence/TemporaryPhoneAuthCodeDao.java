package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.TemporaryPhoneAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TemporaryPhoneAuthCodeDao extends JpaRepository<TemporaryPhoneAuthCode,String>{
    @Modifying
    @Transactional
    TemporaryPhoneAuthCode save(TemporaryPhoneAuthCode temporaryPhoneAuthCode);

    TemporaryPhoneAuthCode findByPhone(String phone);
}
