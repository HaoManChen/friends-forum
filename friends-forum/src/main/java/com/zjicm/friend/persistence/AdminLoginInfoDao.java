package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.AdminLoginInfoTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginInfoDao extends JpaRepository<AdminLoginInfoTable,String> {
    AdminLoginInfoTable save(AdminLoginInfoTable adminLoginInfoTable);
    AdminLoginInfoTable findByLoginName(String loginName);
}
