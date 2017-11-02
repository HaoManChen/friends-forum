package com.zjicm.friend.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 从身份证上提取信息
 */
public class IdentityCardNumberInfoUtil {
    /**
     *
     * @param indentityCardNumber
     * @return
     *  返回年纪，生日，性别
     */
    public static Map<String,String> getInfoFromIdentityCard(String indentityCardNumber){
        Date date = new Date();
        Map<String,String> map = new HashMap<String,String>();
        String birthYear = indentityCardNumber.substring(6,10);
        String old = Integer.parseInt(DateUtil.getDate("yyyy",date))-Integer.parseInt(birthYear)+"";
        String sex;
        if (Integer.parseInt(indentityCardNumber.substring(16,17))%2==0){
            sex = "女";
        }else {
            sex = "男";
        }
        String birthday = indentityCardNumber.substring(6,14);
        map.put("old",old);
        map.put("sex",sex);
        map.put("birthday",birthday);
        return map;
    }

    public static void main(String[] args) {
        Map<String,String> map = getInfoFromIdentityCard("330282199607296972");
        System.err.println(map.values());
    }
}
