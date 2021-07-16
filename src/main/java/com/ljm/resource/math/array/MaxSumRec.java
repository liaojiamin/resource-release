package com.ljm.resource.math.array;

import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 16:38 2021/1/29
 */
public class MaxSumRec {
    public static int[] getArrayData(int size) {
        int[] arrayData = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int temp = random.nextInt(50);
            if (temp > 25) {
                arrayData[i] = temp;
            } else {
                int value = temp - 2 * temp;
                arrayData[i] = value;
            }

        }
        return arrayData;
    }

    /**
     * 分治算法
     * */
    public static int getMaxSumRec_1(int[] arrayData){
        return getMaxSumRecDivide(arrayData, 0, arrayData.length -1);
    }

    public static int getMaxSumRecDivide(int[] arrayData, int left, int right){
        if(left == right){
            return arrayData[left] > 0 ? arrayData[left] : -1;
        }
        int center = (left + right)/2;
        int maxLeft = getMaxSumRecDivide(arrayData, left, center);
        int maxRight = getMaxSumRecDivide(arrayData, center + 1, right);

        int maxLeftBordeSum =0;
        int leftBordeSum = 0;
        for(int i = center; i>= left; i--){
            leftBordeSum+=arrayData[i];
            if(maxLeftBordeSum < leftBordeSum){
                maxLeftBordeSum = leftBordeSum;
            }
        }

        int maxRightBordeSum = 0;
        int rightBordeSum = 0;
        for(int i = center+1; i<=right;i++){
            rightBordeSum+=arrayData[i];
            if(maxRightBordeSum < rightBordeSum){
                maxRightBordeSum = rightBordeSum;
            }
        }
        return Math.max(Math.max(maxLeft, maxRight), maxLeftBordeSum+maxRightBordeSum);
    }

    public static int getMaxSumRec_2(int[] arrayData){
        int maxSum = 0, thisSum = 0;
        for (int i = 0; i < arrayData.length; i++) {
            thisSum+=arrayData[i];
            if(thisSum > maxSum){
                maxSum = thisSum;
            }else if (thisSum < 0){
                //如果之前项累计不大于0， 则清空之前项和，重新计数
                thisSum = 0;
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {
        int[] arraydata = getArrayData(20);
        for (int i = 0; i < arraydata.length; i++) {
            System.out.print(arraydata[i] + ", ");
        }
        System.out.println();
        System.out.println(getMaxSumRec_1(arraydata));
        System.out.println();
        System.out.println(getMaxSumRec_2(arraydata));
    }
}
