package com.zjicm.friend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDate(String format,Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    public static boolean dateCompare(Date date1,Date date2){
        Long dateLong1 = date1.getTime();
        Long dateLong2 = date2.getTime();
        if (Math.abs(dateLong1-dateLong2)>300000) {
            return false;
        }else{
            return true;
        }
    }
    public static void main (String[] args){
        Date date = new Date();
        Date date2 = new Date();
        System.out.println(date2.getTime()-date.getTime());
        System.err.println(getDate("yyyyMMddHHmmss",new Date()));
    }
}
