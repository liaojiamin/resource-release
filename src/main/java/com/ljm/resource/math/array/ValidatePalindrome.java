package com.ljm.resource.math.array;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode 9）
 * @author liaojiamin
 * @Date:Created in 17:13 2022/2/24
 */
public class ValidatePalindrome {
    public static void main(String[] args) {
        System.out.println(8%2);
        System.out.println(2147483647 % 10);
        System.out.println();
        System.out.println(isPalindrome(22));
    }
    /**
     * 得到回文，用异或操作看是否一致：x=2147483647 时候回文无法回去，int类型越界
     * */
    public static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        if(x < 10){
            return true;
        }
        int temp = x;
        int numberSize = 0;
        int roundNum = 0;
        while ( temp > 0) {
            temp /=10;
            numberSize++;
        }
        int realySize = numberSize/2;
        int validateTemp = x;
        while (realySize > 0) {
            roundNum += (validateTemp % 10) * Math.pow(10, realySize - 1);
            validateTemp /=10;
            realySize--;
        }
        if(numberSize % 2 != 0){
            //奇数需要删多一位
            validateTemp /=10;
        }
        return validateTemp == roundNum;
    }
}
