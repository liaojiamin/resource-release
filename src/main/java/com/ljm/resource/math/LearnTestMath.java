package com.ljm.resource.math;

import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 10:51 2022/6/22
 */
public class LearnTestMath {

    public static void main(String[] args) {
        int[] array = quickSort(getArrayData(20));
        for (int i : array) {
            System.out.print(i + ",");
        }

    }

    public static int[] getArrayData(int size) {
        int[] arrayData = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int temp = random.nextInt(50);
            if (temp > 0) {
                arrayData[i] = temp;
            } else {
                int value = temp - 2 * temp;
                arrayData[i] = value;
            }

        }
        return arrayData;
    }

    public static int[] quickSort(int[] array){
        if(array == null || array.length <= 1){
            return array;
        }
        quickSortSwap(array, 0, array.length - 1);
        return array;
    }

    public static void quickSortSwap(int[] array, int left, int right){
        if(left < right){
            int temp = swap(array, left, right);
            quickSortSwap(array, left, temp - 1);
            quickSortSwap(array, temp + 1, right);
        }
    }

    public static int swap(int[] array, int left, int right){
        if(left < right){
            int position = array[left];
            while (left < right){
                while (left < right && array[right] > position){
                    right --;
                }
                if(left < right){
                    array[left] = array[right];
                    left ++;
                }
                while (left < right && array[left] < position){
                    left ++;
                }
                if(left < right){
                    array[right]  = array[left];
                    right --;
                }
            }
           array[left] = position;
        }
        return left;
    }
}
