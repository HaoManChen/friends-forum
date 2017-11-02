package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="admin_login")
public class User {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String adminId;

    @Column(name="admin_login_name")
    private String adminLoginName;

    @Column(name="admin_login_password")
    private String adminLoginPassword;

}
