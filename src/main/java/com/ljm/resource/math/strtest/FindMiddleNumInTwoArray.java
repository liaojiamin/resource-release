package com.ljm.resource.math.strtest;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * @author liaojiamin
 * @Date:Created in 11:03 2021/7/26
 */
public class FindMiddleNumInTwoArray {
    public static void main(String[] args) {
        int[] middleNum1 = new int[2];
        int[] arrayNum1 = {};

        int[] arrayNum2 = {0};

        middleNum1 = findMiddleNum(arrayNum1, arrayNum2);
        for (int i = 0; i < middleNum1.length; i++) {
            System.out.println(middleNum1[i]);
        }
        System.out.println(findMiddleNumOpt(arrayNum1, arrayNum2));

    }

    /**
     * 构造新数组优化版本
     * 合并数组合并到n/2 处即可，时间复杂度O((m+n)/2) 空间复杂度 O(1)
     *
     * */
    public static double findMiddleNumOpt(int[] arrayNum1, int[] arrayNum2){
        if(arrayNum1 == null && arrayNum2 == null ){
            return 0d;
        }
        if(arrayNum1.length <= 0 && arrayNum2.length <= 0){
            return 0d;
        }
        if(arrayNum1 == null || arrayNum1.length <= 0){
            if(arrayNum2.length%2 == 0 && arrayNum2.length >= 2){
                return (arrayNum2[arrayNum2.length/2]+arrayNum2[(arrayNum2.length/2)-1])/2d;
            }else {
                return arrayNum2[arrayNum2.length/2];
            }
        }

        if(arrayNum2 == null || arrayNum2.length <= 0){
            if(arrayNum1.length%2 == 0 && arrayNum1.length >= 2){
                return (arrayNum1[arrayNum1.length/2] + arrayNum1[(arrayNum1.length/2)-1])/2d;
            }else {
                return arrayNum1[arrayNum1.length/2];
            }
        }

        int positionNum1 = 0;
        int positionNum2 = 0;
        int positionMax = 0;
        int count = arrayNum1.length+ arrayNum2.length;
        Integer findOne = Integer.MIN_VALUE;
        Integer findTwo = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            int temp = 0;
            if(positionNum1 == arrayNum1.length){
                temp = arrayNum2[positionNum2++];
            }else if(positionNum2 == arrayNum2.length){
                temp = arrayNum1[positionNum1++];
            }else {
                if(arrayNum2[positionNum2] < arrayNum1[positionNum1]){
                    temp = arrayNum2[positionNum2++];
                }else {
                    temp = arrayNum1[positionNum1++];
                }
            }
            if(count%2 == 0){
                if((positionMax == (count/2)-1) || (positionMax == count/2)){
                    if(findOne == Integer.MIN_VALUE){
                        findOne = temp;
                    }else {
                        findTwo = temp;
                    }
                }
            }else {
                if(positionMax == count/2){
                    return temp;
                }
            }
            positionMax++;
            if(positionMax > count/2){
                return (findOne + findTwo)/2d;
            }
        }
        return 0d;
    }

    /**
     * 时间复杂度O(m+n) 空间复杂度O(m+n)
     *
     */
    public static int[] findMiddleNum(int[] arrayNum1, int[] arrayNum2){
        if(arrayNum1 == null && arrayNum2 == null ){
            return null;
        }
        if(arrayNum1.length <= 0 && arrayNum2.length <= 0){
            return null;
        }
        int[] middleNum = new int[2];
        if(arrayNum1 == null || arrayNum1.length <= 0){
            if(arrayNum2.length%2 == 0 && arrayNum2.length >= 2){
                middleNum[0] = arrayNum2[arrayNum2.length/2];
                middleNum[1] = arrayNum2[arrayNum2.length/2-1];
            }else {
                middleNum[0] = arrayNum2[arrayNum2.length/2];
            }
            return  middleNum;
        }

        if(arrayNum2 == null || arrayNum2.length <= 0){
            if(arrayNum1.length%2 == 0 && arrayNum1.length >= 2){
                middleNum[0] = arrayNum1[arrayNum1.length/2];
                middleNum[1] = arrayNum1[arrayNum1.length/2-1];
            }else {
                middleNum[0] = arrayNum1[arrayNum1.length/2];
            }
            return  middleNum;
        }

        int positionNum1 = 0;
        int positionNum2 = 0;
        int positionMax = 0;
        int[] mixTowArray = new int[arrayNum1.length + arrayNum2.length];
        for (int i = 0; i < mixTowArray.length; i++) {
            if(positionNum1 == arrayNum1.length){
                mixTowArray[positionMax++] = arrayNum2[positionNum2++];
            }else if(positionNum2 == arrayNum2.length){
                mixTowArray[positionMax++] = arrayNum2[positionNum1++];
            }else {
                if(arrayNum2[positionNum2] < arrayNum1[positionNum1]){
                    mixTowArray[positionMax++] = arrayNum2[positionNum2++];
                }else {
                    mixTowArray[positionMax++] = arrayNum2[positionNum1++];
                }
            }
        }
        if(mixTowArray.length%2 == 0){
            middleNum[0] = mixTowArray[mixTowArray.length/2];
            middleNum[1] = mixTowArray[mixTowArray.length/2-1];

        }else {
            middleNum[0] = mixTowArray[mixTowArray.length/2];
        }
        return middleNum;
    }
}
