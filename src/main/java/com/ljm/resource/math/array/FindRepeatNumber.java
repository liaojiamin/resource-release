package com.ljm.resource.math.array;

import java.util.HashSet;

/**
 * Created by jiamin5 on 2022/12/5.
 * 剑指 Offer 03. 数组中重复的数字：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        int targetNum[] = {3,5,3,5,3,4,2,1,5,6,7,3};
        System.out.println(findRepeatNum(targetNum));
        System.out.println(findREpeatNum_haseSet(targetNum));
    }

    /**
     * 桶排序原理
     * */
    public static int findRepeatNum(int[] number){
        Integer[] bucketArray = new Integer[number.length];

        for (int i = 0; i < number.length; i++) {
            if(bucketArray[number[i]] == null){
                bucketArray[number[i]] = number[i];
                continue;
            }
            return number[i];
        }
        return -1;
    }

    /**
     * 利用HashSet
     * */
    public static int findREpeatNum_haseSet(int[] number){
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < number.length; i++) {
            if(hashSet.contains(number[i])){
                return number[i];
            }
            hashSet.add(number[i]);
        }
        return -1;
    }
}
