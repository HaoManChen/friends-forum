package com.zjicm.friend.service;

import com.zjicm.friend.dto.AdminUserRegisterDTO;
import com.zjicm.friend.dto.DateDTO;
import com.zjicm.friend.dto.PhoneAuthCodeDTO;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public interface RegisterService {
    //存储测试
    JSONObject save(AdminUserRegisterDTO adminUserRegisterDTO,String ip) throws JSONException;
    //发送手机验证码
    JSONObject sendPhoneAuthCode(PhoneAuthCodeDTO phoneAuthCodeDTO) throws JSONException;
    //发送随机生成的虚拟号
    JSONObject sendRandomVirtualNumber(DateDTO dateDTO)throws JSONException;

}
