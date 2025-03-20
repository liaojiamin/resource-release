package com.ljm.resource.math.interview.mylist;

/**
 * Created by jiamin5 on 2023/3/16.
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。

  

 示例 1：

 输入：nums = [3,2,3]
 输出：3
 示例 2：

 输入：nums = [2,2,1,1,1,2,2]
 输出：2
  

 提示：
 n == nums.length
 1 <= n <= 5 * 104
 -109 <= nums[i] <= 109
  

 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/majority-element
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        System.out.println(majorityElement.majorityElement(new int[]{6,5,5}));
    }

    //遍历，每次删2个不同的数，如果剩下一个数
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <=0){
            return -1;
        }
        int thisNum = Integer.MIN_VALUE;
        int countNum = 0;
        for (int num : nums) {
            if(countNum == 0){
                thisNum = num;
                countNum ++;
            }else if(thisNum == num){
                countNum ++;
            }else {
                countNum --;
            }
        }
        if(countNum == 0){
            return -1;
        }
        int realyCount = 0;
        for (int num : nums) {
            if(thisNum == num){
                realyCount ++;
            }
        }
        return realyCount > nums.length/2 ? thisNum : -1;
    }
}
