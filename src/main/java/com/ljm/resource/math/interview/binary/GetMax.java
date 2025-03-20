package com.ljm.resource.math.interview.binary;

/**
 * Created by jiamin5 on 2023/3/28.
 * 递归案例，最大值查找
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return getMax(arr, 0, arr.length -1);
    }

    public static int getMax(int[] arr, int l, int r) {
        if (arr == null) {
            return -1;
        }
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int leftMax = getMax(arr, l, mid);
        int rightMax = getMax(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2,5,7,9,4,5,6,6,34,534,534,453,4};
        System.out.println(getMax(arr));
    }
}
