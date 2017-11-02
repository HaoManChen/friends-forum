package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.AdminTruthInfoTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTruthInfoDao extends JpaRepository<AdminTruthInfoTable,String> {
    AdminTruthInfoTable save(AdminTruthInfoTable adminTruthInfoTable);
}
