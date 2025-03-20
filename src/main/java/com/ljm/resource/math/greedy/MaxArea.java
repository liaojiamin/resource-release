package com.ljm.resource.math.greedy;

/**
 * Created by jiamin5 on 2023/3/6.
 */
public class MaxArea {
    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea.maxArea(height));
    }

    public int maxArea(int[] height) {
        if(height == null || height.length <= 0){
            return 0;
        }
        int i=0;
        int j = height.length-1;
        int max = 0;
        while (i < j){
            int now = (j - i)* Math.min(height[i], height[j]);
            if(max < now){
                max = now;
            }
            //总认为有更大的，当i更矮，则i找更大，当j更矮，则j找更大的
            if(height[i] < height[j]){
                i++;
            }else {
                j--;
            }
        }
        return max;
    }
}
