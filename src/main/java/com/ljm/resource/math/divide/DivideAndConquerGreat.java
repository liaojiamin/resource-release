package com.ljm.resource.math.divide;

import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 12:06 2021/2/1
 */
public class DivideAndConquerGreat {

    public static void main(String[] args) {
        int[] beginArrayData = getArrayData(30);
        System.out.println("------------------");
        int[] arrayData = quickSort(beginArrayData);
        for (int i = 0; i < arrayData.length; i++) {
            System.out.println(arrayData[i]);
        }
    }

    public static int[] quickSort(int[] arrayData) {
        if (arrayData == null || arrayData.length <= 1) {
            return arrayData;
        }
        return quickSort(arrayData, 0, arrayData.length - 1);
    }

    public static int[] quickSort(int[] arrayData, int left, int right) {
        if(Math.abs(left - right) <= 20){
            insertionSort(arrayData, left, right);
        }else {
            if (left < right) {
                int position = swap(arrayData, left, right);
                quickSort(arrayData, left, position - 1);
                quickSort(arrayData, position + 1, right);
            }
        }
        return arrayData;
    }

    /**
     * 快排主体实现
     */
    public static int swap(int[] arrayData, int left, int right) {
        int position = median3(arrayData, left, right);
        int i = left ;
        int j = right - 1;
        while (i < j) {
            while (i < j && arrayData[i] <= position) {
                i++;
            }
            while (i < j && arrayData[j] >= position) {
                j--;
            }
            if (i < j) {
                swapElement(arrayData, i, j);
            }
        }
        //position初始位置是right-1
        swapElement(arrayData, i, right - 1);
        return i;
    }


    /**
     * 数据交换
     */
    public static void swapElement(int[] arrayData, int i, int j) {
        int temp = arrayData[i];
        arrayData[i] = arrayData[j];
        arrayData[j] = temp;
    }


    /**
     * 三数中值获取
     */
    public static int median3(int[] arrayData, int left, int right) {
        int center = (left + right) / 2;
        if (arrayData[center] < arrayData[left]) {
            swapElement(arrayData, center, left);
        }
        if (arrayData[right] < arrayData[left]) {
            swapElement(arrayData, right, left);
        }
        if (arrayData[right] < arrayData[center]) {
            swapElement(arrayData, right, center);
        }
        swapElement(arrayData, center, right - 1);
        return arrayData[right - 1];
    }


    /**
     * 插入排序
     */
    public static int[] insertionSort(int[] arraydata, int left, int right) {
        if (arraydata == null || arraydata.length <= 1) {
            return arraydata;
        }
        for (int i = 0; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (arraydata[j - 1] > arraydata[j]) {
                    int temp = arraydata[j - 1];
                    arraydata[j - 1] = arraydata[j];
                    arraydata[j] = temp;
                }
            }
        }
        return arraydata;
    }

    /**
     * 随机生成数列
     */
    public static int[] getArrayData(int size) {
        int[] arrayData = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int temp = random.nextInt(10);
            if (temp > 0) {
                arrayData[i] = temp;
            } else {
                int value = temp - 2 * temp;
                arrayData[i] = value;
            }
            System.out.println(arrayData[i]);

        }
        return arrayData;
    }
}
