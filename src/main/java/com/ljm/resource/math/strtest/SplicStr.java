package com.ljm.resource.math.strtest;

import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 14:53 2020/11/4
 */
public class SplicStr {
    /**
     * 时间复杂度O(n),空间复杂度O(1)
     * @author: liaojiamin
     * @description: 拼接两个排序的数组,target 足够容纳两个数组
     * @date: 14:54 2020/11/4
     *
     * @return 
     */
    public static int[] splicStr(int[] target, int[] origin){
        if(null == target || null == origin){
            return null;
        }
        int targetLength = 0;
        while (target[targetLength] != '\0'){
            targetLength ++;
        }
        int resultLength = targetLength + origin.length;
        if(target.length < resultLength){
            return null;
        }
        int resultPosition = resultLength - 1;
        int targetPosition = targetLength - 1;
        int originPosition = origin.length - 1;
        while (resultPosition >= 0){
            if(targetPosition >= 0 && originPosition >= 0
                    && target[targetPosition] > origin[originPosition]){
                target[resultPosition--] = target[targetPosition--];
            }else {
                target[resultPosition--] = origin[originPosition--];
            }
        }
        return target;
    }

    public static void main(String[] args) {
        int[] target = new int[20];
        int[] origin = new int[10];
        for (int i = 0; i < 10; i++) {
            target[i] = i+300;
            origin[i] = i*10;
        }
        int[] targetInt = splicStr(target, origin);
        for (int i : targetInt) {
            System.out.print(i + " ");
        }
    }
}
