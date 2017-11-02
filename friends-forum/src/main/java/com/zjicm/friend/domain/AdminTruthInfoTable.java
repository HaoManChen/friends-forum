package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_truth_info_table")
public class AdminTruthInfoTable {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String adminId;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "old")
    private String old;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "apartment")
    private String apartment;
    @Column(name = "identity_card_number")
    private String identityCardNumber;
    @Column(name = "creat_time")
    private String creatTime;
    @Column(name = "latest_update_time")
    private String latestUpdateTime;
    @Column(name = "is_delete")
    private String isDelete;
}
