package com.zjicm.friend.config;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Creared by ChenHao on 2017/08/21
 * 返回信息配置类
 */
public class MsgConstant {

    private final static Map<String, String> msg = new HashMap<>();

    public final static String MSG_SUCCESS = "000000";
    public final static String MSG_INVOKING_THIRD_PARTY_FALSE = "000001";
    public final static String MSG_ERROR_INSIDE= "000002";
    public final static String MSG_AUTH_CODE_TIME_OUT = "000003";
    public final static String MSG_NOT_FIND_AUTH_CODE = "000004";
    public final static String MSG_ERROR_AUTH_CODE = "000005";
    public final static String MSG_SAME_VIRTUAL_INFO = "000006";
    public final static String MSG_CANT_FIND_USERNAME = "000007";
    public final static String MSG_PASSWORD_WRONG ="000008";
    public final static String MSG_NO_DATA = "000009";
    public final static String MSG_SEND_TIME_OUT = "010000";

    static {
        //正常返回
        msg.put(MSG_SUCCESS, "ok");
        //系统异常
        msg.put(MSG_INVOKING_THIRD_PARTY_FALSE, "调用第三方接口失败");
        msg.put(MSG_ERROR_INSIDE, "内部错误");
        msg.put(MSG_AUTH_CODE_TIME_OUT, "验证码有效时间已过！");
        msg.put(MSG_NOT_FIND_AUTH_CODE, "未找到该手机的验证码记录，请重新发送！");
        msg.put(MSG_ERROR_AUTH_CODE, "验证码错误");
        msg.put(MSG_SAME_VIRTUAL_INFO, "已存在该虚拟号信息");
        msg.put(MSG_CANT_FIND_USERNAME, "用户名不存在!");
        msg.put(MSG_SEND_TIME_OUT,"发送超时");
        msg.put(MSG_PASSWORD_WRONG,"密码错误");
        msg.put(MSG_NO_DATA," 没有数据");

    }

    public static String getMsg(String key) {
        return msg.get(key);
    }

    public static JSONObject getJsonMsg(String key) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("errCode",key);
        json.put("errMessage",getMsg(key));
        return json;
    }
    public static JSONObject getJsonMsg(String key,String data) throws JSONException {
        JSONObject json = new JSONObject();
        json = MsgConstant.getJsonMsg(key);
        json.put("data",data);
        return json;
    }
}