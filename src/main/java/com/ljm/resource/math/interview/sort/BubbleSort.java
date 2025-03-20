package com.ljm.resource.math.interview.sort;

/**
 * Created by jiamin5 on 2023/3/20.
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] myarray = new int[]{1, 4, 3, 2, 66, 3, 34, 45, 6, 7, 6, 435, 34, 23, 54, 567, 45, 6};
        myarray = bubbleSort.bubbleSort(myarray);
        for (int i : myarray) {
            System.out.print(i + ", ");
        }

    }

    public int[] bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (array[j-1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

}
