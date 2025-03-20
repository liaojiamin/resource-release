package com.ljm.resource.math.interview.binary;

import java.util.Arrays;

/**
 * Created by jiamin5 on 2023/3/21.
 */
public class BSExists {

    public static boolean exist(int[] array, int num){
        if(array == null || array.length <= 0){
            return false;
        }
        int left = 0;
        int right = array.length -1;
        int middle = 0;
        while ( left <= right){
            //middle = left + (right-left)/2
            middle = left + (right - left) >> 1;
//             middle = (right+left)/2;
            if(array[middle] == num){
                return true;
            }else if(array[middle] > num){
                right = middle -1;
            }else {
                left = middle + 1;
            }
        }
        return false;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
