package com.ljm.resource.math.strtest;

/**
 * @author liaojiamin
 * @Date:Created in 15:31 2021/7/9
 */
public class StringToInt {

    public static void main(String[] args) {
        System.out.println((int) '0');
        System.out.println((int) '1');
        System.out.println((int) '2');
        System.out.println((int) '3');
        System.out.println((int) '4');
        System.out.println((int) '5');
        System.out.println((int) '6');
        System.out.println((int) '7');
        System.out.println((int) '8');
        System.out.println((int) '9');
        System.out.println(strToInt("-2147483649"));
    }

    public static int strToInt(String str) {
        if (str == null || str.length() <= 0) {
            return -1;
        }
        char[] numberChar = str.toCharArray();
        Long num = 0L;
        int carry = 1;
        int position = 0;
        if (numberChar[position] == '+') {
            position++;
            carry = 1;
        } else if (numberChar[position] == '-') {
            position++;
            carry = -1;
        }
        int length = numberChar.length;
        for (int i = position; i < length; i++) {
            char positionChar = numberChar[i];
            if (positionChar > '9' || positionChar < '0') {
                return -1;
            }
            int positionNum = positionChar - '0';
            Long power = 1L;
            for (int i1 = 0; i1 < length - i - 1; i1++) {
                power *= 10;
            }
            num += (positionNum) * power;
            if (carry == 1 && (num*carry > Integer.MAX_VALUE)) {
                return -1;
            }
            if (carry == -1 && (num*carry < Integer.MIN_VALUE)) {
                return -1;
            }
        }
        int result = (int) (num * carry);
        return result;
    }
}
