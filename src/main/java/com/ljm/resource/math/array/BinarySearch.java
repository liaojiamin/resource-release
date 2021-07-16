package com.ljm.resource.math.array;

/**
 * 递归实现二分查找
 * @author liaojiamin
 * @Date:Created in 15:43 2021/4/13
 */
public class BinarySearch {
    public static int binarySearchNum(int[] array, int target){
        return binarySearchNum(array, target, 0, array.length -1);
    }

    public static int binarySearchNum(int[] array, int target, int left, int right){
        if(array == null){
            return -1;
        }
        if(left < 0 || right > array.length-1){
            return -1;
        }
        if(left > right){
            return -1;
        }
        int middle = (left + right)/2;
        if(array[middle] == target){
            return middle;
        }
        if(array[middle] > target){
            return binarySearchNum(array, target, left, middle-1);
        }
        if(array[middle] < target){
            return binarySearchNum(array, target, middle+1, right);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {2,6,7,8,11,32,42,55,67,89,89,89,89,89,89,89,90,91,92,93,94,95,96};
        System.out.println(binarySearchNum(array, 89));
    }
}
