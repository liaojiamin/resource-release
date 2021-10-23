package com.ljm.resource.math.strtest;

/**
 * 删除给定字符串中出现次数最小的字符，如果出现次数相同并且都是最小，则都删除
 * @author liaojiamin
 * @Date:Created in 15:34 2021/7/21
 */
public class RemoveMinCountChar {

    public static void main(String[] args) {
        char[] chars = removeMinCountChar("aabcddefsssddlsfhaksfnaskdlfhahaiamcopiamco");
        for (char aChar : chars) {
            System.out.print(aChar);
        }
    }

    /**
     * 时间复杂度 O(n)， 空间复杂度O(n+256)
     * 空间换时间,纯英文字母，无乱码以及其他字符情况下实现方案
     * */
    public static char[] removeMinCountChar(String str){
        if(str == null || str.length() <= 0){
            return null;
        }
        char[] chars = str.toCharArray();
        int[] charCount = new int[256];
        for (int i = 0; i < chars.length; i++) {
            int position = chars[i] - 'a';
            charCount[position] = charCount[position] + 1;
        }
        int min = chars.length;
        for (int i = 0; i < 256; i++) {
            if(charCount[i] != 0 && charCount[i] < min){
                min = charCount[i];
            }
        }
        char[] newChars = new char[str.length()];
        int position = 0;
        for (int i = 0; i < chars.length; i++) {
            if(charCount[chars[i] -'a'] > min){
                newChars[position++] = chars[i];
            }
        }
        return newChars;
    }
}
