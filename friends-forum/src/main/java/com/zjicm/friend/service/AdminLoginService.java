package com.zjicm.friend.service;

import com.zjicm.friend.dto.AdminLoginDTO;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
public interface AdminLoginService {
    //登陆验证
    JSONObject adminLogin(AdminLoginDTO adminLoginDTO) throws JSONException;
}
