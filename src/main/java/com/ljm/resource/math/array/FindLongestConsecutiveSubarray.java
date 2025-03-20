package com.ljm.resource.math.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamin5 on 2023/4/13.
 */
public class FindLongestConsecutiveSubarray {
    public static void main(String[] args) {
        System.out.println(findLongestConsecutiveSubarray(new int[]{1,2,3,4,5,2,3,5,6,7,6,452,352,43,234,2342,34}));
    }

    public static int findLongestConsecutiveSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = 1;
        int maxLen = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(nums[left], 1);

        while (right < n) {
            int count = countMap.getOrDefault(nums[right], 0);
            countMap.put(nums[right], count + 1);

            while (count > 0) {
                int leftCount = countMap.get(nums[left]);
                countMap.put(nums[left], leftCount - 1);
                left++;
                count = countMap.getOrDefault(nums[right], 0);
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

}
