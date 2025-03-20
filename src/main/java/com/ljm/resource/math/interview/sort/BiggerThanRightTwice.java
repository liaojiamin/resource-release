package com.ljm.resource.math.interview.sort;

/**
 * Created by jiamin5 on 2023/3/30.
 * https://leetcode.com/problems/reverse-pairs/
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * <p>
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BiggerThanRightTwice {
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    public static int process(int nums[], int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(nums, l, mid)
                + process(nums, mid+1, r)
                + merge(nums, l, mid, r);
    }

    public static int merge(int nums[], int l, int m, int r) {
        int num = 0;
        int windowR = m + 1;
        for (int i = l; i < m+1; i++) {
            while (windowR <=r && (long)nums[i] > (long)nums[windowR]*2){
                windowR++;
            }
            num += windowR - (m+1);
        }
        int[] help = new int[r - l + 1];
        int i =0;
        int p1 = l;
        int p2 = m+1;
        while (p1 <=m && p2 <= r){
            help[i++] = nums[p1] < nums[p2] ? nums[p1++]: nums[p2++];
        }
        while (p1<=m){
            help[i++] = nums[p1++];
        }

        while (p2<=r){
            help[i++] = nums[p2++];
        }
        for (int i1 = 0; i1 < help.length; i1++) {
            nums[l + i1] = help[i1];
        }
        return num;
    }


    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for(int j = i+1;j< arr.length;j++){
                ans+=arr[i] > 2*arr[j]? 1:0;
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePairs(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }



}







