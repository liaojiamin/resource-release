package com.ljm.resource.math.array;

/**
 * Created by jiamin5 on 2023/3/9.
 */
public class MinSubArrayLen {
    public static void main(String[] args) {
        int[] targetArray = new int[]{10,2,3};
        System.out.println();
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        System.out.println(minSubArrayLen.minSubArrayLen(6 , targetArray));
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if(nums.length == 1){
            if(nums[0] == target){
                return 1;
            }else {
                return 0;
            }
        }
        int maxPosition = nums.length - 1;
        int minPosition = maxPosition - 1;
        int sum = nums[maxPosition] + nums[minPosition];
        int maxCount = 0;
        while (minPosition >= 0) {
            if(nums[minPosition] >= target || nums[maxPosition] >= target){
                return 1;
            }
            if(sum < target){
                minPosition--;
                if(minPosition >= 0){
                    sum+=nums[minPosition];
                }
            }
            if(sum >= target){
                int count = maxPosition - minPosition +1;
                if(maxCount == 0 || maxCount > count){
                    maxCount = count;
                }
                maxPosition --;
                minPosition = maxPosition -1;
                if(minPosition >=0 && maxPosition >=0){
                    sum = nums[maxPosition] + nums[minPosition];
                }
            }
        }
        return maxCount;
    }

    public int[] sortNums(int[] nums, int start, int end) {
        if (start < end) {
            int middle = swap(nums, start, end);
            sortNums(nums, start, middle - 1);
            sortNums(nums, middle + 1, end);
        }
        return nums;
    }

    public int swap(int[] nums, int left, int right) {
        if (left < right) {
            int position = nums[left];
            while (left < right) {
                while (left < right && nums[right] > position) {
                    right--;
                }
                if (left < right) {
                    nums[left] = nums[right];
                    left++;
                }
                while (left < right && nums[left] < position) {
                    left++;
                }
                if (left < right) {
                    nums[right] = nums[left];
                    right--;
                }
            }
            nums[left] = position;
        }
        return left;
    }
}
