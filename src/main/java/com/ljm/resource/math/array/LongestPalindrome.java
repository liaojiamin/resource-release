package com.ljm.resource.math.array;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamin5 on 2023/3/5.
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。

 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 输入:s = "abccccdd"
 输出:7
 解释:
 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("sdscccccdd"));
    }
    /**
     * a -- 1
     * ab -- 0
     * abc -- 0
     * abcc -- 3
     * abccc -- 3
     * abcccc -- 5
     * abccccd -- 5
     * abccccdd - 7
     * 思路  统计成对出现字符串个数 n对 ，如果除了成对出现字符意外还有字符剩，则 n*2+1 是最长的结果
     * */
    public static int longestPalindrome(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        Map<Character, Integer> numberMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(numberMap.containsKey(s.charAt(i))){
                numberMap.put(s.charAt(i),  numberMap.get(s.charAt(i)) + 1);
            }else {
                numberMap.put(s.charAt(i), 1);
            }
        }
        boolean hasSingleChar = false;
        Integer count = 0;
        for (Character character : numberMap.keySet()) {
            if(numberMap.get(character) == 1){
                hasSingleChar = true;
            }else if (numberMap.get(character) >= 2){
                if(numberMap.get(character) % 2 != 0){
                    hasSingleChar = true;
                }
                count+= (numberMap.get(character)/2)*2;
            }
        }
        return  hasSingleChar ? count + 1 : count;
    }
}
