package com.zjicm.friend.test;

import com.zjicm.friend.domain.User;
import org.apache.log4j.Logger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class MyTest {
//    private static Logger log = Logger.getLogger(MyTest.class);
//    private volatile double a;
    public static void main(String[] args){
//        log.trace("======trace");
//        log.debug("======debug");
//        log.info("======info");
//        log.warn("======warn");
//        log.error("======error");
//        Map<Integer,String> map = new ConcurrentHashMap<>();
//        LocalDate date = LocalDate.of(2014, Month.JULY,10);
//        Instant instant = Instant.now();
//        LocalDateTime dateTime = date.atTime(20,12);
//        System.out.println(dateTime);
//        date = date.with(lastDayOfMonth());
//        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String as = dateTimeFormatter.format(dateTime);
//        dateTime = LocalDateTime.parse("2014-07-10 20:12:00",dateTimeFormatter);
//        System.out.println(instant.toEpochMilli());
//        map.put(1,"a");
//        map.forEach((k,va)-> System.err.println("key"+k+"value"+va));
//        Counter counter = new Counter();
//        Thread thread1 = new Thread(counter, "A");
//        Thread thread2 = new Thread(counter, "B");
//        thread1.start();
//        thread2.start();
//        int a = 0;
//        switch (1){
//            case 1 : a = 1;
//            default: a = 2;
//        }
//        System.out.println(a);
        int a = 0 ;
        a = a+a++;
        System.out.println(a);
    }
}
