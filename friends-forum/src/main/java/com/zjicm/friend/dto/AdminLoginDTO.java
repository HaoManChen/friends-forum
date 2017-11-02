package com.zjicm.friend.dto;

import lombok.Data;

/**
 * 管理员后台登陆
 */
@Data
public class AdminLoginDTO {
    //用户名
    private String loginName;
    //密码
    private String loginPassword;
    //图片验证码
    private String authCode;
}
