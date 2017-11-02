package com.zjicm.friend.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Util {
    /**
     * 32位MD5加密
     * @param str 要加密的字符串
     * @return 加密后字符串
     * @author dandyzheng
     * @date 2013-11-22
     * @see
     */
    public static String EncoderByMd5(String str){
        StringBuffer md5Str = new StringBuffer("");
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] b = md5.digest(str.getBytes("utf-8"));
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16){
                    md5Str.append("0");
                }
                md5Str.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Str.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(EncoderByMd5("123456"));
    }
}
