package com.zjicm.friend.util;

public class AuthCodeUtil {
    /**
     * 生成随机数字验证码
     * @param digit
     *            随机数的位数
     */
    public static String getAuthCode(int digit){

        String randomNumber =""+ (int)(Math.random()*10);
//        System.out.println(sum);
        if (digit ==1){
            return randomNumber;
        }
        if (digit<1){
            return null;
        }
        return randomNumber+getAuthCode(digit-1);
    }


    public static void main(String[] args){
        for (int i = 0 ;i <200 ;i++)
        System.out.println(getAuthCode(4));
    }
}
