package com.ljm.resource.math.array;

/**
 * Created by jiamin5 on 2023/3/9.
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindPeakElement {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        FindPeakElement findPeakElement = new FindPeakElement();
        System.out.println(findPeakElement.findPeakElement(nums));
        System.out.println(findPeakElement.findPeakElement(nums, 0, nums.length - 1));

    }

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }


    public int findPeakElement(int[] num, int start, int end) {
        while (start < end) {
            int middle = start + ((end - start) / 2);
            if (middle == 0 && num[middle] > num[middle + 1]) {
                return middle;
            }
            if (middle == num.length - 1 && num[middle] > num[middle - 1]) {
                return middle;
            }
            if (middle - 1 > 0
                    && middle + 1 < num.length
                    && num[middle] > num[middle - 1]
                    && num[middle] > num[middle + 1]) {
                return middle;
            }
            if (num[middle] > num[middle + 1]) {
                end = middle;
            }
            if (num[middle] < num[middle + 1]) {
                start = middle + 1;
            }
        }
        return start;
    }
}
