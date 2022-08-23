package com.ljm.resource.math.array;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode 27）
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * @author liaojiamin
 * @Date:Created in 16:19 2022/2/24
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] array = {3,2,2,3,2,3,5,5,6,7,3,2,2,2,4,4,5,6,4,3,2,2,4,6,6,2,6};
        int result = removeElement(array, 2);
        System.out.println(result);
        for (int i = 0; i < result; i++) {
            System.out.println(array[i]);
        }
    }

    public static int removeElement(int[] array, int val){
        if(array == null || array.length <= 0){
            return 0;
        }
        int positionBegin = 0;
        int positionEnd = array.length - 1;
        while (positionBegin < positionEnd){
            while (positionBegin < positionEnd && array[positionBegin] != val){
                positionBegin ++;
            }
            while (positionBegin < positionEnd && array[positionEnd] == val){
                positionEnd --;
            }
            int temp = array[positionBegin];
            array[positionBegin] = array[positionEnd];
            array[positionEnd] = temp;
        }
        if(array[positionBegin] == val){
            return positionBegin;
        }
        return positionBegin + 1;
    }
}
