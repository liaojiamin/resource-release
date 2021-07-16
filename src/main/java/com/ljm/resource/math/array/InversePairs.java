package com.ljm.resource.math.array;

import java.util.Arrays;

/**
 * 找出数组中所有逆序对 例如：
 * {7,5,6,4} => 75,76,74,54,64
 * @author liaojiamin
 * @Date:Created in 14:50 2021/6/11
 */
public class InversePairs {
    public static Integer countInverse = 0;

    /**
     * 归并排序模拟
     * */
    public static Integer[]  mergeSortFindPairs(Integer[] array){
        if(array == null || array.length <= 1){
            return array;
        }
        if(array.length < 2){
            return array;
        }
        Integer middle = array.length/2;
        Integer[] left = Arrays.copyOfRange(array, 0, middle);
        Integer[] right = Arrays.copyOfRange(array, middle, array.length);
        return merge(mergeSortFindPairs(left), mergeSortFindPairs(right));
    }

    public static Integer[] merge(Integer[] left, Integer[] right){
        Integer[] mergeArray = new Integer[left.length + right.length];
        Integer targetPosition = mergeArray.length-1;
        Integer leftPosition = left.length -1;
        Integer rightPosition = right.length -1;
        while (targetPosition >= 0){
            if(leftPosition >=0 && rightPosition >= 0){
                if(left[leftPosition] > right[rightPosition]){
                    countInverse+=(rightPosition+1);
                    mergeArray[targetPosition--] = left[leftPosition--];
                }else {
                    mergeArray[targetPosition--] = right[rightPosition--];
                }
            }else {
                if(leftPosition < 0){
                    while (rightPosition >= 0){
                        mergeArray[targetPosition--] = right[rightPosition--];
                    }
                }else {
                    while (leftPosition >= 0){
                        mergeArray[targetPosition--] = left[leftPosition--];
                    }
                }
            }
        }
        return mergeArray;
    }

    public static void main(String[] args) {
        Integer[] array = {1,3,44,22,31,4,0,32,14,16,32,9,4,7,23,555,12,123,456};
        array = mergeSortFindPairs(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        System.out.println(countInverse);
    }
}
