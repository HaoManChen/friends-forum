package com.zjicm.friend.client;

import com.zjicm.friend.config.PhoneAuthCodeConfig;
import com.zjicm.friend.util.Base64Util;
import com.zjicm.friend.util.DateUtil;
import com.zjicm.friend.util.MD5Util;
import com.zjicm.friend.util.SocketUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * 容联云通信短信验证码发布平台接口连接(未通)
 * 接口文档链接URL: http://www.yuntongxun.com/doc/rest/sms/3_2_2_2.html
 * @author 陈灏
 * @date 2017-08-18
 * @see
 */
public class RestMessageAuthCodeClient {
    private static final Log logger = LogFactory.getLog(RestMessageAuthCodeClient.class);
    //开发者主账户id
    private static final String ACCOUNT_SID = "8aaf07085def8a38015df409972f01f4";
    //账户授权令牌
    private static final String AUTH_TOKEN = "000659cb8dd74196b92964d3f2685016";
    //模板短信API引用的地址有Base URL
    private static final String Rest_URL = "https://sandboxapp.cloopen.com:8883";
    //要上线的appid
    private static final String APP_ID = "8aaf07085def8a38015df409977d01f9";


    public  static boolean clientToRest(String phoneNumber,String authCode,String time){
        //开发者主账户
        String accountSid = ACCOUNT_SID;
        //生成时间戳date
        Date nowTime = new Date();
        String date = DateUtil.getDate("yyyyMMddHHmmss",nowTime);
        //按接口要求生成SigParameter
        String sigParameterUnencrypted = ACCOUNT_SID+AUTH_TOKEN+date;
        logger.info("未加密前SigParameter ："+sigParameterUnencrypted);
        String sigParameterEncrypted = MD5Util.EncoderByMd5(sigParameterUnencrypted).toUpperCase();
        //按接口要求生成请求URL
        String url = Rest_URL+"/2013-12-26/Accounts/"+accountSid+"/SMS/TemplateSMS?sig="+sigParameterEncrypted;
        logger.info("接口统一请求包头为"+url);
        //按接口要求生成 Authorization
        String authorizationUnencrypted = accountSid+":"+date;
        logger.info("未加密前 Authorization ："+ authorizationUnencrypted);
        //Base64加密 Authorization
        String authorizationEncrypted = Base64Util.getBase64(authorizationUnencrypted);
        logger.info("加密后 Authorization ："+ authorizationEncrypted);
        //请求包体
        JSONObject json = new JSONObject();
        String strings = "[\""+authCode+"\",\""+ time+"\"]";
        try {
            json.put("appId",APP_ID);
            json.put("to",phoneNumber);
            json.put("templateId","1");
            json.put("datas",strings);
        }catch (JSONException e){
            logger.error("clientToRestJSON数据生成错误");
        }
        logger.info("生成的JSON数据为"+json.toString());
        //发送Https请求
        String result = SocketUtil.httpsPost(url,json.toString(),"application/json","application/json;charset=utf-8",authorizationEncrypted);
        System.err.println(result);
        return true;
    }


    public static void main (String[] args){
        clientToRest("17826836140","1234","5");
    }



}
