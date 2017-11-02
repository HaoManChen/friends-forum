package com.zjicm.friend.dto;

import lombok.Data;

import java.util.Date;

/**
 * 接收注册表信息
 */
@Data
public class AdminUserRegisterDTO {
    private String loginName;
    private String loginPassword;
    private Date date;
    private String name;
    private String email;
    private String phone;
    private String telephone;
    private String apartment;
    private String identityCardNumber;
    private String userName;
    private String virtualNumber;
    private String gmLevel;
    private String gmLevelAuthCode;
    private String messageAuthCode;
}
