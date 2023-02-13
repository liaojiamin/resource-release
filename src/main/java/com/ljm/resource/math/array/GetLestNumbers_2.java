package com.ljm.resource.math.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jiamin5 on 2023/2/13.
 */
public class GetLestNumbers_2 {
    public static void main(String[] args) {
        int[] arrayData = new int[4];
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            arrayData[i] = random.nextInt(50);
            System.out.print(arrayData[i]+",");
        }
        findLastKNumbers(arrayData, 3);

    }
    public static void findLastKNumbers(int[] array, int k){
        if(array == null || array.length <= 0){
            return;
        }
        if(array.length <= k){
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
            return;
        }
        System.out.println();
        int[] lastKNumber = getLastNumbers(0, array.length -1, array, k);
        for (int i = 0; i < lastKNumber.length; i++) {
            System.out.print(lastKNumber[i]+",");
        }
    }
    public static int[] getLastNumbers(Integer left, Integer right, int[] array, Integer k) {
        if (left < right) {
            Integer middle = swap(left, right, array);
            if (middle == k) {
                return Arrays.copyOfRange(array, 0, middle);
            }
            getLastNumbers(left, middle - 1, array, k);
            getLastNumbers(middle + 1, right, array, k);
        }
        return Arrays.copyOfRange(array, 0, k);
    }

    public static Integer swap(Integer left, Integer right, int[] array) {
        if (left < right) {
            int temp = array[left];
            while (left < right) {
                while (left < right && array[right] > temp) {
                    right--;
                }
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                while (left < right && array[left] < temp) {
                    left++;
                }
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            }
            array[left] = temp;
        }
        return left;
    }
}
