package com.ljm.resource.math.array;

import java.util.List;
import java.util.Stack;

/**
 * 求字符串的全排列
 *
 * @author liaojiamin
 * @Date:Created in 12:07 2021/5/24
 */
public class Permutation {

    public static void main(String[] args) {
        permutationArray("ertgf");
//        combination("abcde".toCharArray());
    }

    public static void permutationArray(String arr) {
        char[] pChars = arr.toCharArray();
        permutationArray(pChars, 0);
    }

    /**
     * 求解字符串的全排列
     *
     * */
    public static void permutationArray(char[] pChars, Integer start){
        if(pChars == null || pChars.length <= 1 || start == pChars.length - 1){
            printArray(pChars);
        }
        for (int i = start;i<=pChars.length-1;i++){
            char temp = pChars[i];
            pChars[i] = pChars[start];
            pChars[start] = temp;
            permutationArray(pChars, start +1);
            temp = pChars[i];
            pChars[i] = pChars[start];
            pChars[start] = temp;
        }
    }

    public static void combination(char[] pChars){
        if(pChars == null){
            return;
        }
        Stack<Character> combinaChar = new Stack<>();
        for (Integer i = 1; i <= pChars.length; i++) {
            combination(pChars, 0, i, combinaChar);
        }
    }

    /**
     * 求解字符串的所有组合
     * */
    public static void combination(char[] pChars, Integer begin, Integer number, Stack<Character> combinaChar){
        if(number <= 0){
            System.out.println(combinaChar.toString());
            return;
        }
        if(begin == pChars.length){
            return;
        }
        //第一个字符放入组合中
        combinaChar.push(pChars[begin]);
        combination(pChars, begin +1, number-1, combinaChar);
        //第一个字符不放入组合中
        combinaChar.pop();
        combination(pChars, begin+1, number, combinaChar);
    }


    public static void printArray(char[] pChars){
        if(pChars == null || pChars.length <= 1){
            return;
        }
        for (char pChar : pChars) {
            System.out.print(pChar+",");
        }
        System.out.println();
    }
}


