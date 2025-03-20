package com.ljm.resource.math.interview.sort;

/**
 * Created by jiamin5 on 2023/3/20.
 */
public class ChooseSort {
    public static void main(String[] args) {
        ChooseSort chooseSort = new ChooseSort();
        int[] myarray = new int[]{1,4,3,2,66,3,34,45,6,7,6,435,34,23,54,567,45,6};
        myarray = chooseSort.chooseSort(myarray);
        for (int i : myarray) {
            System.out.print(i+ ", ");
        }

    }

    public int[] chooseSort(int[] array){
        if(array == null || array.length <= 1){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minPosition = i;
            for (int j = i+1;j<array.length;j++){
                minPosition = array[minPosition] > array[j] ? j : minPosition;
            }
            int temp = array[minPosition];
            array[minPosition] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
