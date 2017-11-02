package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.AdminVirtualInfoTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminVirtualInfoDao extends JpaRepository<AdminVirtualInfoTable,String> {
    AdminVirtualInfoTable findByVirtualNumber(String virtualNumber);
    AdminVirtualInfoTable save(AdminVirtualInfoTable adminVirtualInfoTable);
}
