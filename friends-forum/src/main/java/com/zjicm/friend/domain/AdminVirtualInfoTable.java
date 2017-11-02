package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_virtual_info_table")
public class AdminVirtualInfoTable {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String adminId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "virtual_number")
    private String virtualNumber;
    @Column(name = "latest_login_time")
    private String latestLoginTime;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "latest_login_ip")
    private String latestLoginIp;
    @Column(name = "most_frequently_login_ip")
    private String mostFrequentlyLoginIp;
    @Column(name = "gm_level")
    private String gmLevel;
    @Column(name = "latest_update_time")
    private String latestUpdateTime;
    @Column(name = "is_delete")
    private String isDelete;
}
