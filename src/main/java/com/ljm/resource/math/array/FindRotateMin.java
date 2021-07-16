package com.ljm.resource.math.array;

/**
 * 查找旋转数组中的最小值
 * @author liaojiamin
 * @Date:Created in 11:39 2021/3/16
 */
public class FindRotateMin {

    public static int findMin(int[] array){
        if(array == null || array.length <= 0){
            return -1;
        }
        if(array.length == 1){
            return array[0];
        }
        if(array.length == 2){
            return array[0]> array[1] ? array[1] : array[0];
        }
        int index1 = 0;
        int index2 = array.length -1;
        int indexMin = index1;
        while (array[index1] >= array[index2]){
            if(index2 - index1 == 1){
                indexMin = index2;
                break;
            }
            indexMin = (index2 + index1)/2;
            if(array[indexMin] >= array[index1]){
                index1 = indexMin;
            }else if(array[indexMin] <= array[index1]){
                index2 = indexMin;
            }
        }
        return indexMin;
    }

    public static void main(String[] args) {
        int[] array = {3,4,5,0,1,2};
        System.out.println(array[findMin(array)]);
    }
}
