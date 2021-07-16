package com.ljm.resource.math.strtest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 10:57 2021/6/9
 */
public class FindUglyNumber {

    public static void main(String[] args) {
//        Long time = System.currentTimeMillis();
//        System.out.println(getNumberOfUglyNumber(1500));
//        Long time_1 = System.currentTimeMillis();
//        System.out.println(time_1 - time);
//
//        System.out.println(getNumberOfUglyNumber_1(1500));
        Long time_2 = System.currentTimeMillis();
//        System.out.println(time_2 - time_1);

        System.out.println(getNumberOfUglyNumber_2(1500));
        System.out.println(System.currentTimeMillis() - time_2);

    }

    /**
     * 方法三：不逐个判断，我们先将1 是基础丑数放入已经查找数组中，此时最大丑数是1 记为max，
     * 因为 uglyNum * （2/3/5） 还是uglyNum，我们直接找正好大于 当前max的最小丑数，
     * 此时将1 分别* （2/3/5） 得到 （2/3/5），其中最小值2，此时 max = 2
     * 以此类推
     * */
    public static Integer getNumberOfUglyNumber_2(Integer position){
        if(position <= 0){
            return -1;
        }
        Integer[] uglyArray = new Integer[position];
        uglyArray[0] = 1;
        Integer nowPosition = 1;
        Integer max = uglyArray[0];
        while (nowPosition < position){
            Integer newMax = findMinUgly(max, uglyArray, nowPosition);
            max = newMax;
            uglyArray[nowPosition] = newMax;
            nowPosition++;
        }

        return uglyArray[position - 1];

    }

    public static Integer findMinUgly(Integer max, Integer[] uglyArray, Integer nowPosition){
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i< nowPosition ; i++) {
            Integer temp_5 = uglyArray[i] * 5;
            if(temp_5 < max){
                continue;
            }
            if(temp_5 > max && temp_5 < min){
                min = temp_5;
            }
            Integer temp_2 = uglyArray[i] * 2;
            if(temp_2 > max && temp_2 < min){
                min = temp_2;
            }
            Integer temp_3 = uglyArray[i] * 3;
            if(temp_3 > max && temp_3 < min){
                min = temp_3;
            }

        }
        return min;
    }

    /**
     * 方法二：保存之前判断过的丑数，丑数* (2/3/5) 必然还是丑数
     * 更慢
     * */
    public static Integer getNumberOfUglyNumber_1(Integer position){
        if(position <=0 ){
            return -1;
        }
        List<Integer> uglyNumberList = new ArrayList<>();
        Integer count =0;
        for (int i=0;true;i++){
            if(isUglyNumber(i, uglyNumberList)){
                count ++;
                if(count.equals(position)){
                    return i;
                }
                uglyNumberList.add(i);
            }
        }
    }

    /**
     * 判断是否丑数
     * */
    public static boolean isUglyNumber(Integer number, List<Integer> uglyNumberList) {
        if(number <= 0){
            return false;
        }
        while (number % 2 == 0) {
            number /= 2;
            if(uglyNumberList.contains(number)){
                return true;
            }
        }
        while (number % 3 == 0) {
            number /= 3;
            if(uglyNumberList.contains(number)){
                return true;
            }
        }
        while (number % 5 == 0){
            number /= 5;
            if(uglyNumberList.contains(number)){
                return true;
            }
        }
        return number == 1;
    }

    /**
     * 方法一：逐个判断从1 开始的数字是否丑数
     * */
    public static Integer getNumberOfUglyNumber(Integer position){
        if(position <= 0){
            return -1;
        }
        Integer count = 0;
        for (int i =0; true;i++){
            if(isUglyNumber(i)){
                count++;
                if(count.equals(position)){
                    return i;
                }
            }
        }
    }

    /**
     * 判断是否丑数
     * */
    public static boolean isUglyNumber(Integer number) {
        if(number <= 0){
            return false;
        }
        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0){
            number /= 5;
        }
        return number == 1;
    }
}
