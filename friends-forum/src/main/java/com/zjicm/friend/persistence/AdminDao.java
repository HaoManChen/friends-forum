package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface AdminDao extends JpaRepository<User,String>{
    @Modifying
    @Transactional
    User save(User user);

}
