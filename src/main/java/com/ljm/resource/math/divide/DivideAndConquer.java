package com.ljm.resource.math.divide;

import java.util.Random;

/**
 * 分治-第k个最小/大元素问题解法
 *
 * @author liaojiamin
 * @Date:Created in 14:53 2021/1/28
 */
public class DivideAndConquer {
    public static int[] quickSort(int[] arrayData) {
        if (arrayData == null || arrayData.length <= 1) {
            return arrayData;
        }
        return quickSort(arrayData, 0, arrayData.length - 1);
    }

    public static int[] quickSort(int[] arrayData, int left, int right) {
        if (left < right) {
            int position = swap(arrayData, left, right);
            quickSort(arrayData, left, position - 1);
            quickSort(arrayData, position + 1, right);
        }
        return arrayData;
    }

    public static int swap(int[] arrayData, int left, int right) {
        int position = median3(arrayData, left, right);
        int i = left;
        int j = right-1;
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
        arrayData[right-1] = arrayData[i];
        arrayData[i] = position;
        return i;
    }

    public static void swapElement(int[] arrayData, int i, int j) {
        int temp = arrayData[i];
        arrayData[i] = arrayData[j];
        arrayData[j] = temp;
    }


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


    public static int quickSelect(int[] arrayData, int k) {
        if (arrayData == null || arrayData.length <= 0 || k > arrayData.length - 1) {
            return -1;
        }
        if (arrayData.length == 1) {
            return arrayData[0];
        }
        return quickSelect(arrayData, 0, arrayData.length - 1, k);

    }

    public static Integer swapReferences(int[] arrayData, int left, int right) {
        if (left < right) {
            int position = arrayData[left];
            while (left < right) {
                while (left < right && arrayData[right] > position) {
                    right--;
                }
                if (left < right) {
                    arrayData[left] = arrayData[right];
                    left++;
                }
                while (left < right && arrayData[left] < position) {
                    left++;
                }
                if (left < right) {
                    arrayData[right] = arrayData[left];
                    right--;
                }
            }
            arrayData[left] = position;
        }
        return left;
    }


    public static int quickSelect(int[] arraydata, int left, int right, int k) {
        if (left < right) {
            int position = swapReferences(arraydata, left, right);
            if (position > k) {
                return quickSelect(arraydata, left, position - 1, k);
            } else {
                return quickSelect(arraydata, position + 1, right, k);
            }
        }
        return arraydata[k];
    }

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


    public static void main(String[] args) {
        int[] beginArrayData = getArrayData(10);
//        int k = 3;
//        int key = quickSelect(beginArrayData, 3);
//        System.out.println("第 "+k +"个最小元素是" + key);
        System.out.println("------------------");
        int[] arrayData = quickSort(beginArrayData);
        for (int i = 0; i < arrayData.length; i++) {
            System.out.println(arrayData[i]);
        }
    }
}
