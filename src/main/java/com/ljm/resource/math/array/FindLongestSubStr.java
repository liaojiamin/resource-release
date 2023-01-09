package com.ljm.resource.math.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，找出字符串中的最长子串，使得这个子串中没有重复的数字
 *
 * @author liaojiamin
 * @Date:Created in 16:39 2021/7/15
 */
public class FindLongestSubStr {

    public static void main(String[] args) {
        String maxSubStr = "12335781";
        System.out.println(findLongestStr(maxSubStr));
        System.out.println(lengthOfLongestSubstring(maxSubStr));
    }

    /**
     * double fix
     * 双指针二次实现
     */
    public static Integer lengthOfLongestSubstring(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        Set<Character> longestStr = new HashSet<>();
        int positionEnd = 0;
        int positionsStart = 0;
        int maxLength = 0;
        char[] targetStr = str.toCharArray();
        while (positionEnd <= targetStr.length - 1
                && positionsStart <= targetStr.length - 1
                && positionsStart <= positionEnd) {
            if (longestStr.contains(targetStr[positionEnd])) {
                longestStr.remove(targetStr[positionsStart]);
                positionsStart++;
            } else {
                longestStr.add(targetStr[positionEnd]);
                positionEnd++;
            }
            int strLength = positionEnd - 1 - positionsStart + 1;
            maxLength = strLength > maxLength ? strLength : maxLength;
        }
        return maxLength;
    }

    /**
     * 双指针实现滑动窗口，并空间换时间
     */
    public static Integer findLongestStr(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        Map<Character, Integer> validateMap = new HashMap<>();
        char[] chars = str.toCharArray();
        int positionStart = 0;
        int positionEnd = 0;
        int maxSumLength = 0;
        while (positionEnd <= chars.length - 1 && positionStart <= positionEnd) {
            if (validateMap.containsKey(chars[positionEnd])) {
                validateMap.remove(chars[positionStart]);
                positionStart++;
            } else {
                validateMap.put(chars[positionEnd], 1);
                positionEnd++;
            }
            int length = positionEnd - 1 - positionStart + 1;
            if (maxSumLength < length) {
                maxSumLength = length;
            }
        }
        return maxSumLength;
    }

}
