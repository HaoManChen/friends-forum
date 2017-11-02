package com.zjicm.friend.service.impl;

import com.zjicm.friend.config.MsgConstant;
import com.zjicm.friend.domain.AdminLoginInfoTable;
import com.zjicm.friend.dto.AdminLoginDTO;
import com.zjicm.friend.persistence.AdminLoginInfoDao;
import com.zjicm.friend.service.AdminLoginService;
import com.zjicm.friend.util.MD5Util;
import org.apache.log4j.Logger;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChenHao on 2017/08/27
 *管理员登录服务实现类
 */
@Service("AdminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {
    private static final Logger logger = Logger.getLogger(AdminLoginServiceImpl.class);
    @Autowired
    private AdminLoginInfoDao adminLoginInfoDao;
    @Override
    public JSONObject adminLogin(AdminLoginDTO adminLoginDTO) throws JSONException{
        String loginName = adminLoginDTO.getLoginName();
        String loginPassword = adminLoginDTO.getLoginPassword();
        //对密码进行MD5加密
        loginPassword = MD5Util.EncoderByMd5(loginPassword);
        //获取该用户信息
        AdminLoginInfoTable adminLoginInfoTable = adminLoginInfoDao.findByLoginName(loginName);
        String password;
        try{
            password = adminLoginInfoTable.getLoginPassword();
        }catch (Exception e){
            logger.info("Cant find username :"+loginName);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_CANT_FIND_USERNAME);
        }
    if ((!"".equals(password))&&(!password.equals(loginPassword))){
            logger.info("Password is Error :"+loginPassword+"by User: "+loginName);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_PASSWORD_WRONG);
    }
        return MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
    }
}
