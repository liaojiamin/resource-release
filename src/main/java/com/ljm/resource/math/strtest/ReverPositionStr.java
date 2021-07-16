package com.ljm.resource.math.strtest;

/**
 * 指定位置反转字符串
 * 案例 I am a software engineer 4 -> a software engineerI am
 * @author liaojiamin
 * @Date:Created in 18:47 2021/7/1
 */
public class ReverPositionStr {
    public static void main(String[] args) {
        char[] target = "I am a software engineer".toCharArray();
        char[] result = reverPosition(target, 4);
        for (char c : result) {
            System.out.print(c);
        }
        System.out.println();
        char[] result_1 = reverPositionTwice(target, 4);
        for (char c : result_1) {
            System.out.print(c);
        }
    }
    /**
     * 方案二：反转两次
     * 时间复杂度O(n)
     *
     * */
    public static char[] reverPositionTwice(char[] target, int position){
        if(target == null || target.length <= 0 || target.length <= position){
            return target;
        }
        target = reverAll(target, 0, target.length -1);
        target = reverAll(target, 0, target.length-1 - position);
        target = reverAll(target, target.length-1 - position + 1, target.length-1);
        return target;
    }

    /**
     * 按指定位置反转
     * */
    public static char[] reverAll(char[] target, int start, int end){
        while (start < end){
            char temp = target[start];
            target[start] = target[end];
            target[end] = temp;
            start++;
            end--;
        }
        return target;
    }


    /**
     * 方案一：空间换时间
     * 时间复杂度O(n)，空间复杂度O(n)
     * */
    public static char[] reverPosition(char[] target, int position){
        if(target == null || target.length <= 0 || target.length <= position){
            return target;
        }
        char[] result = new char[target.length];
        int positionResult = 0;
        for (int i = position; i < target.length; i++) {
            result[positionResult++]=target[i];
        }
        for(int i=0;i<position;i++){
            result[positionResult++] = target[i];
        }
        return result;
    }
}
