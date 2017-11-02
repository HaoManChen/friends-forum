package com.zjicm.friend.util;

import com.zjicm.friend.config.RandomEngineConfig;

import java.util.Random;

public class RandomEngineUtil {
    /**
     * 生成乱序的数组
     * @param startNumber
     *          顺序时的起始数字
     * @return
     */
    public static long[] getRandomNumber(long startNumber){
        //得到数字的数量
        int groupMembers = RandomEngineConfig.MAX_GROUP_MEMBERS;
        //创建顺序数组
        long[] startArray = new long[groupMembers];
        //创建随机数组
        long[] resultArray = new long[groupMembers];
        //将有顺序的数装入顺序数组
        for (int i = 0; i<groupMembers;i++){
            startArray[i] = i+startNumber;
        }
        for (int i=0 ; i <groupMembers;i++){
            int seed = random(0, startArray.length - i);//从剩下的随机数里生成
            resultArray[i] = startArray[seed];//赋值给结果数组
            startArray[seed] = startArray[startArray.length - i - 1];//把随机数产生过的位置替换为未产生的
        }
        return resultArray;
    }

    /**
     * 返回在min和max之间的随机数（包含）
     * @param min
     * @param max
     * @return
     */
    private static int random(int min ,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
    public static void main (String[] args){
        RandomEngineUtil randomEngineUtil = new RandomEngineUtil();
        long a = 11;
        long[] num = randomEngineUtil.getRandomNumber(10);
        for (long i : num) {
            System.out.println(i);
        }
    }
}
