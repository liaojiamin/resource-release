package com.ljm.resource.math.array;

/**
 * @author liaojiamin
 * @Date:Created in 11:05 2021/5/31
 */
public class FindGreatestSumOfSubArray {
    public static void main(String[] args) {
        Integer[] array = {-1,-2,3,-10,-4,-7,-8,-9,-10,100};
        System.out.println(findGreatestSum(array));
        System.out.println(1023658%10%4);
    }
    /**
     * 顺序遍历求连续子数组的最大和
     * */
    public static Integer findGreatestSum(Integer[] array){
        if(array == null || array.length <= 0){
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        int temp = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(temp == Integer.MIN_VALUE){
                temp = array[i];
            }else {
                temp+=array[i];
            }
            if(temp > max){
                max = temp;
            }
        }
        return max;
    }
}
