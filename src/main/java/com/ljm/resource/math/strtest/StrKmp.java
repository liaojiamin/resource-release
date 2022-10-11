package com.ljm.resource.math.strtest;

/**
 * Created by jiamin5 on 2022/2/12.
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * 来源：力扣（LeetCode第28题）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 */
public class StrKmp {
    public static void main(String[] args) {
        System.out.println(strKmp("asdfuohadsuoufhuuowyqruoqiwhgbyur23iu4y2oi34hf", "uoq"));
    }

    /**
     * 	KMP算法是一种改进的字符串匹配算法，由D.E.Knuth，J.H.Morris和V.R.Pratt提出的，因此人们称它为克努特—莫里斯—普拉特操作（简称KMP算法）。
     * 	KMP算法的核心是利用匹配失败后的信息，尽量减少模式串与主串的匹配次数以达到快速匹配的目的。
     * 	具体实现就是通过一个next()函数实现，函数本身包含了模式串的局部匹配信息。KMP算法的时间复杂度O(m+n)。
     *
     * */
    public static int strKmp(String haystack, String needle){
        if (needle == null && haystack == null) {
            return 0;
        }
        if (haystack == null || haystack.length() <= 0) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        char[] hasChar = haystack.toCharArray();
        char[] needChar = needle.toCharArray();
//        int[] next = {-1,0,0,1,2,0};
//        int[] next = {-1,0,0};
        int [] next = getNext(needChar);
        int i = 0, j = 0;
        while (i<hasChar.length && j < needChar.length){
            if(j == -1 || hasChar[i] == needChar[j]){
                i++;
                j++;
            }else {
                j=next[j];
            }
        }
        if(j == needChar.length){
            return i-j;
        }
        return -1;
    }

    public static int[] getNext(char[] needChar){
        int[] next = new int[needChar.length+1];
        int j = 0, k = -1;
        next[j] = k;
        while (j < needChar.length){
            if(k == -1 || needChar[j] == needChar[k]){
                j++;
                k++;
                next[j] = k;
            }else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 双循环破解，时间复杂度O(M*N) 空间复杂度O(N)
     */
    public static int strStr(String haystack, String needle) {
        if (needle == null && haystack == null) {
            return 0;
        }
        if (haystack == null || haystack.length() <= 0) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        char[] hasChar = haystack.toCharArray();
        char[] needChar = needle.toCharArray();
        for (int i = 0; i < hasChar.length; i++) {
            if (hasChar[i] != needChar[0]) {
                continue;
            }
            boolean isValidate = true;
            for (int j = needChar.length - 1; j >= 0; j--) {
                if (hasChar[i + j] != needChar[j]) {
                    isValidate = false;
                    break;
                }
            }
            if (isValidate) {
                return i;
            } else {
                continue;
            }
        }
        return -1;

    }
    /**
     * 模式匹配BM暴力破解写法
     * */
    public static int strBM(String haystack, String needle){
        if (needle == null && haystack == null) {
            return 0;
        }
        if (haystack == null || haystack.length() <= 0) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        char[] hasChar = haystack.toCharArray();
        char[] needChar = needle.toCharArray();
        int i = 0, j = 0;
        while (i<hasChar.length && j < needChar.length){
            if(hasChar[i] == needChar[j]){
                i++;
                j++;
            }else {
                i=i-j+1;
                j=0;
            }
        }
        if(j == needChar.length){
            return i-j;
        }
        return -1;
    }
}
