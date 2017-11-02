package com.zjicm.friend.service.impl;

import com.zjicm.friend.client.RestMessageAuthCodeClient;
import com.zjicm.friend.config.PhoneAuthCodeConfig;
import com.zjicm.friend.config.RandomEngineConfig;
import com.zjicm.friend.config.ServerConfig;
import com.zjicm.friend.core.jpa.MyUUIDGenerator;
import com.zjicm.friend.domain.*;
import com.zjicm.friend.dto.AdminUserRegisterDTO;
import com.zjicm.friend.dto.DateDTO;
import com.zjicm.friend.dto.PhoneAuthCodeDTO;
import com.zjicm.friend.persistence.*;
import com.zjicm.friend.service.RegisterService;
import com.zjicm.friend.util.*;
import com.zjicm.friend.config.MsgConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created By ChenHao on 2017/08/20
 * 注册服务实现类
 */
@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService {
    private static final Log logger = LogFactory.getLog(RegisterServiceImpl.class);
    @Autowired
    AdminDao adminDao;
    @Autowired
    TemporaryPhoneAuthCodeDao temporaryPhoneAuthCodeDao;
    @Autowired
    VirtualNumberSaveMainDao virtualNumberSaveMainDao;
    @Autowired
    AdminVirtualInfoDao adminVirtualInfoDao;
    @Autowired
    AdminTruthInfoDao adminTruthInfoDao;
    @Autowired
    AdminLoginInfoDao adminLoginInfoDao;

    /**
     * 注册信息验证实现
     * @param adminUserRegisterDTO
     * @return
     * @throws JSONException
     */
    @Override
    public JSONObject save(AdminUserRegisterDTO adminUserRegisterDTO,String ip) throws JSONException{
        //建立管理员真实信息实体类
        AdminTruthInfoTable adminTruthInfoTable = new AdminTruthInfoTable();
        //建立管理员虚拟信息实体类
        AdminVirtualInfoTable adminVirtualInfoTable ;
        //建立管理员登陆信息实体类
        AdminLoginInfoTable adminLoginInfoTable = new AdminLoginInfoTable();
        //获取系统时间
        Date dateNow = new Date();
        if(!DateUtil.dateCompare(dateNow,adminUserRegisterDTO.getDate())){
            logger.error("注册超时");
            return MsgConstant.getJsonMsg(MsgConstant.MSG_SEND_TIME_OUT);
        }
        //获取短信验证码
        String messageAuthCode = adminUserRegisterDTO.getMessageAuthCode();
        //获取注册手机
        String phone  = adminUserRegisterDTO.getPhone();
        TemporaryPhoneAuthCode temporaryPhoneAuthCode = temporaryPhoneAuthCodeDao.findByPhone(phone);
        String findMessageAuthCode = "";
        try{
            findMessageAuthCode = temporaryPhoneAuthCode.getAuthCode();
        }catch (Exception e){
            logger.info("not find auth code by this phone（未找到该号码对应验证码）by phone："+phone);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_NOT_FIND_AUTH_CODE);
        }
        //得到验证码发送时间
        long date = Long.parseLong(temporaryPhoneAuthCode.getDate());
        if (dateNow.getTime()-date>Long.parseLong(PhoneAuthCodeConfig.PHONE_MESSAGE_EFFECTIVE_TIME)*60000){
            logger.info("Over effective time of auth code(验证码已失效) by phone:"+phone);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_AUTH_CODE_TIME_OUT);
        }
        //比对验证码
        if (!messageAuthCode.equals(temporaryPhoneAuthCode.getAuthCode())){
            logger.info("Error in auth code(验证码错误) by phone:"+phone);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_AUTH_CODE);
        }
        //至此，验证码通过
        //提取身份证上的有效信息
        Map<String,String> map = IdentityCardNumberInfoUtil.getInfoFromIdentityCard(adminUserRegisterDTO.getIdentityCardNumber());
        //验证虚拟号是否已被使用
        String virtualNumber = adminUserRegisterDTO.getVirtualNumber();
        adminVirtualInfoTable = adminVirtualInfoDao.findByVirtualNumber(virtualNumber);
        try{
            adminVirtualInfoTable.getAdminId();
            logger.error("Find same virtualInfo in db(该虚拟号被使用，虚拟号应该自动生成不应该重复才对)by phone:"+phone);
            return MsgConstant.getJsonMsg(MsgConstant.MSG_SAME_VIRTUAL_INFO);
        }catch (Exception e){
            logger.info("not find virtualInfo ,do next");
        }
        //清理adminVirtualInfoTable
        adminVirtualInfoTable = new AdminVirtualInfoTable();
        //开始填入真实信息实体类
        //将身份信息存入实体类
        adminTruthInfoTable.setBirthday(map.get("birthday"));
        adminTruthInfoTable.setOld(map.get("old"));
        adminTruthInfoTable.setSex(map.get("sex"));
        //时间验证通过，将创建时间填入实体类
        adminTruthInfoTable.setCreatTime(dateNow.getTime()+"");
        //短信验证码通过，将手机号码存入实体类
        adminTruthInfoTable.setPhoneNumber(phone);
        //将其他信息存入实体类
        adminTruthInfoTable.setEmail(adminUserRegisterDTO.getEmail());
        adminTruthInfoTable.setApartment(adminUserRegisterDTO.getApartment());
        adminTruthInfoTable.setIdentityCardNumber(adminUserRegisterDTO.getIdentityCardNumber());
        adminTruthInfoTable.setLatestUpdateTime(dateNow.getTime()+"");
        adminTruthInfoTable.setName(adminUserRegisterDTO.getName());
        adminTruthInfoTable.setTelephone(adminUserRegisterDTO.getTelephone());
        adminTruthInfoTable.setIsDelete(ServerConfig.NOT_DELETE);
        //开始填入虚拟信息实体类
        System.err.println(adminUserRegisterDTO.getGmLevel());
        adminVirtualInfoTable.setIsDelete(ServerConfig.NOT_DELETE);
        adminVirtualInfoTable.setGmLevel(adminUserRegisterDTO.getGmLevel());
        adminVirtualInfoTable.setLatestLoginTime(dateNow.getTime()+"");
        adminVirtualInfoTable.setLatestUpdateTime(dateNow.getTime()+"");
        adminVirtualInfoTable.setCreateTime(dateNow.getTime()+"");
        adminVirtualInfoTable.setUserName(adminUserRegisterDTO.getUserName());
        adminVirtualInfoTable.setVirtualNumber(virtualNumber);
        adminVirtualInfoTable.setLatestLoginIp(ip);
        adminVirtualInfoTable.setMostFrequentlyLoginIp(ip);
        //开始填入登陆信息实体类
        adminLoginInfoTable.setCreateTime(dateNow.getTime()+"");
        adminLoginInfoTable.setIsDelete(ServerConfig.NOT_DELETE);
        adminLoginInfoTable.setLatestUpdateTime(dateNow.getTime()+"");
        adminLoginInfoTable.setLoginName(adminUserRegisterDTO.getLoginName());
        adminLoginInfoTable.setLoginPassword(MD5Util.EncoderByMd5(adminUserRegisterDTO.getLoginPassword()));
        //生成admin_id的uuid
        String adminId = MyUUIDGenerator.createUUID();
        adminLoginInfoTable.setAdminId(adminId);
        adminTruthInfoTable.setAdminId(adminId);
        adminVirtualInfoTable.setAdminId(adminId);
        //开始存储信息
        try {
            adminLoginInfoDao.save(adminLoginInfoTable);
            adminTruthInfoDao.save(adminTruthInfoTable);
            adminVirtualInfoDao.save(adminVirtualInfoTable);
            virtualNumberSaveMainDao.updateIsDeleteByVirtualNumber(ServerConfig.IS_DELETE,virtualNumber);
        }catch (Exception e){
            logger.error("info save error");
            return MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_INSIDE);
        }
        return MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
    }

    /**
     * 短信验证码接口实现
     * @param phoneAuthCodeDTO
     * @return JsonErrorMessage
     * @throws JSONException
     */
    @Override
    public JSONObject sendPhoneAuthCode(PhoneAuthCodeDTO phoneAuthCodeDTO) throws JSONException {
        //获取系统时间
        Date dateNow = new Date();
        //获取手机号码
        String phone = phoneAuthCodeDTO.getPhone();
        //获取发送时间
        Date date = phoneAuthCodeDTO.getDate();
        boolean is  = false;
        //获取验证码
        String authCode = AuthCodeUtil.getAuthCode(PhoneAuthCodeConfig.PHONE_MESSAGE_AUTH_CODE_DIGIT);
        //获取短信有效时间
        String time = PhoneAuthCodeConfig.PHONE_MESSAGE_EFFECTIVE_TIME;
        JSONObject json = new JSONObject();
        TemporaryPhoneAuthCode temporaryPhoneAuthCode = new TemporaryPhoneAuthCode();
        if (DateUtil.dateCompare(date,dateNow)){
            temporaryPhoneAuthCode.setDate(dateNow.getTime()+"");
            temporaryPhoneAuthCode.setAuthCode(authCode);
            temporaryPhoneAuthCode.setPhone(phone);
        }else{
            logger.info("对方发送至后台时间过长，原因（对方发出信息时间和后台接收到消息时间超过五分钟），认定（对方系统环境异常）");
            json =MsgConstant.getJsonMsg(MsgConstant.MSG_SEND_TIME_OUT);
            return json;
        }
        try {
            is = RestMessageAuthCodeClient.clientToRest(phone,authCode,time);
            if (is){
                json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
                logger.info("往Rest接口发送通讯请求成功");
                temporaryPhoneAuthCodeDao.save(temporaryPhoneAuthCode);
            }else{
                json = MsgConstant.getJsonMsg(MsgConstant.MSG_INVOKING_THIRD_PARTY_FALSE);
                logger.error("Rest接口连接成功但是未成功发送短信，建议检查（接口文档是否修改）");
            }

        }catch (Exception e){
            json = MsgConstant.getJsonMsg(MsgConstant.MSG_INVOKING_THIRD_PARTY_FALSE);
            logger.error("往Rest接口发送通讯请求失败,建议检查（接口URL是否改变）（接口文档是否修改)");
        }
        return json;
    }

    /**
     * 虚拟号生成实现类
     * @param dateDTO
     * @return
     * @throws JSONException
     */
    @Override
    public JSONObject sendRandomVirtualNumber(DateDTO dateDTO) throws JSONException {
        JSONObject json;
        //获取服务器系统时间
        Date nowDate = new Date();
        //获取传入时间
        Date date = dateDTO.getDate();
        VirtualNumberSaveMain virtualNumberSaveMain = new VirtualNumberSaveMain() ;
        //判断客户端是否正常
        if (DateUtil.dateCompare(date,nowDate)){
            //尝试第一次获取虚拟号
            virtualNumberSaveMain = virtualNumberSaveMainDao.findFirstByIsDelete(ServerConfig.NOT_DELETE);
            //如果虚拟号不存在，随机虚拟号引擎重新为表生成虚拟号
            try {
                virtualNumberSaveMain.getVirtualNumber();
            }catch (Exception e){
                logger.info("get virtual number false! Reason：(MayBe has run out of main table)(Maybe JPA have error)");
                //寻找已经被注册的虚拟号，用作判断下一个虚拟号序列起始的种子
                virtualNumberSaveMain = virtualNumberSaveMainDao.findFirstByIsDelete(ServerConfig.IS_DELETE);
                //表中无数据就按照初始种子赋值
                try {
                    virtualNumberSaveMain.getVirtualNumber();
                }catch (Exception e2){
                    virtualNumberSaveMain = new VirtualNumberSaveMain();
                    virtualNumberSaveMain.setVirtualNumber(RandomEngineConfig.MIN_VIRTUAL_NUMVER-RandomEngineConfig.MAX_GROUP_MEMBERS+"");
                    logger.info("No anything , add first seed");
                }
                //对取到的已注册虚拟号处理判断下一段虚拟号的开始种子
                String virtualNumber = virtualNumberSaveMain.getVirtualNumber();
                try {
                    virtualNumberSaveMain.getVirtualNumber();
                }catch (Exception e3){
                    logger.error("Oh NO! You have error in DAO or in before try");
                    json = MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_INSIDE);
                    return json;
                }
                long longVirtualNumber = Long.parseLong(virtualNumber);
                long team = 1 + longVirtualNumber / RandomEngineConfig.MAX_GROUP_MEMBERS;
                longVirtualNumber = team * RandomEngineConfig.MAX_GROUP_MEMBERS;
                long repair = longVirtualNumber - RandomEngineConfig.MIN_VIRTUAL_NUMVER;
                if (repair<0){
                    repair = -repair;
                }
                repair = repair%RandomEngineConfig.MAX_GROUP_MEMBERS;
                long[] virtualNumberList = RandomEngineUtil.getRandomNumber(longVirtualNumber+repair);
                //创建随机虚拟号列表
                VirtualNumberSaveMain virtualNumberSaveMainBuider = new VirtualNumberSaveMain();
                //清空原有表
//                virtualNumberSaveMainDao.deleteAllByIsDelete(ServerConfig.IS_DELETE);
                //生成新的预备虚拟号表
                for (int i = 0; i < virtualNumberList.length; i++) {
                    virtualNumberSaveMainBuider.setIsDelete(ServerConfig.NOT_DELETE);
                    virtualNumberSaveMainBuider.setVirtualNumber(virtualNumberList[i] + "");
                    virtualNumberSaveMainBuider.setId(i+"");
                    virtualNumberSaveMainDao.save(virtualNumberSaveMainBuider);
                }
                //第二次查询虚拟号
                virtualNumberSaveMain = virtualNumberSaveMainDao.findFirstByIsDelete(ServerConfig.NOT_DELETE);
            }
            //没找到报错
            try {
                virtualNumberSaveMain.getVirtualNumber();
            }catch (Exception e4){
                logger.error("get have used virtual number false twice!Reason(Maybe JPA have error)");
                json = MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_INSIDE);
                return json;
            }
            //测试用删除
//            virtualNumberSaveMainDao.updateIsDeleteByVirtualNumber(ServerConfig.IS_DELETE,virtualNumberSaveMain.getVirtualNumber());
            json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
            json.put("randomVirtualNumber",virtualNumberSaveMain.getVirtualNumber());
            return json;
        }

        json = MsgConstant.getJsonMsg(MsgConstant.MSG_SEND_TIME_OUT);
        return json;
    }
}
