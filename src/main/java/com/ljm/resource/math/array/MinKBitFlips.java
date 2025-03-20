package com.ljm.resource.math.array;

import java.util.LinkedList;

/**
 * Created by jiamin5 on 2023/3/4.
 * 「995. K 连续位的最小翻转次数」。:https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class MinKBitFlips {

    /**
     * 滑动窗口记录需要翻转的位置
     */
    public static int minKBitFlips_2(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (linkedList.size() > 0 && i > linkedList.getFirst() + k -1) {
                linkedList.removeFirst();
            }
            if (linkedList.size() % 2 == nums[i]) {
                if (i + k > nums.length) {
                    return -1;
                }
                linkedList.addLast(i);
                count++;
            }
        }
        return count;
    }

    public static int minKBitFlips(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                continue;
            }
            if (i + k > nums.length) {
                return -1;
            }
            for (int j = i; j < i + k; j++) {
                //0^1=1, 1^1=0 取代赋值操作
                nums[j] ^= 1;
            }
            count++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) {
                return -1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,1,0,1,1,0};
        int k = 1;
        System.out.println(minKBitFlips(nums, k));
        int[] nums2 = {0,0,0,1,0,1,1,0};
        System.out.println(minKBitFlips_2(nums2, k));
    }
}
