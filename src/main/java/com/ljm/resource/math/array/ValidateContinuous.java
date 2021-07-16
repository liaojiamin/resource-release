package com.ljm.resource.math.array;

import java.util.Random;

/**
 * 扑克牌中随机抽 5 张牌， 判断是否是顺子，注意大小王可以替任何数字
 * @author liaojiamin
 * @Date:Created in 15:07 2021/7/6
 */
public class ValidateContinuous {
    public static void main(String[] args) {
        int[] array = new int[60];
        int position = 0;
        for (int i = 0; i <= 13; i++) {
            if(i==0){
                for (int i1 = 53; i1 < 60; i1++) {
                    array[i1] = 0;
                    position+=1;
                }
            }else {
                for (int i1 = 0; i1 < 4; i1++) {
                    array[position]=i;
                    position++;
                }
            }
        }
        System.out.println(isContinuous(array, 5));

    }

    /**
     * 用数组标识抽取出来的扑克，0-标识大小王，11-j，12-Q，13K
     * 先排序，在判断各个数据直接的间隔差距，差距大于0的数量则不连续
     * 有0以外的对子，不连续
     * */
    public static boolean isContinuous(int[] pukeArray, int number){
        int[] array = new int[number];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int card = pukeArray[random.nextInt(pukeArray.length -1)];
            System.out.println(card);
            array[i] = card;
        }
        int[] bucketArray = bucketSort(array);
        int position = 0;
        for (int i = 0; i < bucketArray.length; i++) {
            if(i != 0 && bucketArray[i] >= 2){
                return false;
            }
            for (int i1 = 0; i1 < bucketArray[i]; i1++) {
                array[position] = i;
                position++;
            }
        }
        Integer countZero = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0){
                countZero ++;
            }
            if(i > 0 && array[i-1] != 0){
                if(array[i] - array[i-1] > countZero){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 桶排序:此处返回通，不返回排序后的数组，用来判断是否有除了0 以外的对子
     * */
    public static int[] bucketSort(int[] array){
        if(array == null || array.length<=0){
            return array;
        }
        int[] bucketArray = new int[14];
        for (int i : array) {
            bucketArray[i]+=1;
        }
        return bucketArray;
    }
}
