package com.zjicm.friend.web;

import com.zjicm.friend.dto.AdminUserRegisterDTO;
import com.zjicm.friend.dto.DateDTO;
import com.zjicm.friend.dto.PhoneAuthCodeDTO;
import com.zjicm.friend.service.RegisterService;
import com.zjicm.friend.util.RequestUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenHao on 2017/08/21
 * 管理员注册控制类
 */
@RestController
public class AdminRegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 验证保存注册者
     * @param adminUserRegisterDTO
     * @return
     * @throws JSONException
     */
    @RequestMapping("/submitRegister")
    public String register(AdminUserRegisterDTO adminUserRegisterDTO,HttpServletRequest request)throws JSONException{
        JSONObject json = new JSONObject();
        String ip = RequestUtil.getIp(request);
        json = registerService.save(adminUserRegisterDTO,ip);
            return json.toString();
    }

    /**
     * 短信验证码接口
     * @param phoneAuthCodeDTO
     * @return
     * @throws JSONException
     */
    @RequestMapping("/sendPhoneAuthCode")
    public String sendPhoneAuthCode(PhoneAuthCodeDTO phoneAuthCodeDTO) throws JSONException{
        JSONObject json = new JSONObject();
        json = registerService.sendPhoneAuthCode(phoneAuthCodeDTO);
        return json.toString();
    }

    /**
     * 随机虚拟号获取接口
     * @param dateDTO
     * @return
     * @throws JSONException
     */
    @RequestMapping("/sendRandomVirtualNumber")
    public String sendRandomVirtualNumber(DateDTO dateDTO)throws JSONException{
        JSONObject json = new JSONObject();
        json = registerService.sendRandomVirtualNumber(dateDTO);
        return json.toString();
    }

}
