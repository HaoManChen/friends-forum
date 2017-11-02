package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ChenHao on 2017/08/21
 * 短信验证码临时存储表
 */
@Entity
@Data
@Table(name = "temporary_phone_auth_code")
public class TemporaryPhoneAuthCode {
    @Id
    @Column(name = "phone")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String phone;
//    @Column(name = "is_delete")
//    private String isDelete;
    @Column(name = "auth_code")
    private String authCode;
    @Column(name = "date")
    private String date;

}
