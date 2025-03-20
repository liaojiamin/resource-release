package com.ljm.resource.math.array;

/**
 * Created by jiamin5 on 2023/3/7.
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

 请必须使用时间复杂度为 O(log n) 的算法。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/search-insert-position
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 输入: nums = [1,3,5,6], target = 5
 输出: 2
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6,7,11};
        int target = 10;
        SearchInsert searchInsert = new SearchInsert();
        System.out.println(searchInsert.searchInsert(nums, target));
    }
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        return searchTargetPosition(nums, 0, nums.length -1, target);

    }

    public int searchTargetPosition(int[] nums, int start, int end, int target){
        if(start > end){
            return start;
        }
        int middle = (start + end)/2;
        if(nums[middle] == target){
            return middle;
        }
        if(nums[middle] < target){
            return searchTargetPosition(nums, middle + 1, end, target);
        }
        return searchTargetPosition(nums, start, middle - 1, target);
    }
}
