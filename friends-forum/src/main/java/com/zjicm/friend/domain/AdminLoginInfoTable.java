package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_login_info_table")
public class AdminLoginInfoTable {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String adminId;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "login_password")
    private String loginPassword;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "latest_update_time")
    private String latestUpdateTime;
    @Column(name = "is_delete")
    private String isDelete;
}
