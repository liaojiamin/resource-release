package com.ljm.resource.math.strtest;

/**
 * 给定一个字符串s，找到s中最长的回文子串
 *
 * @author liaojiamin
 * @Date:Created in 11:01 2021/7/28
 */
public class FindLongestStr {
    public static void main(String[] args) {
        System.out.println(findLongestStr("c"));
    }

    /**
     * 直接循环遍历，判断
     */
    public static String findLongestStr(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() <= 1) {
            return str;
        }

        int maxStrLength = 0;
        String longestStr = "";
        int before = -1;
        int after = -1;
        for (int i = 0; i < str.length(); i++) {
              if (i + 1 <= str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                 before = i;
                 after = i + 1;
                  if (before >= 0 && after >= 0) {
                      while (before >= 0 && after <= str.length() - 1 && str.charAt(before) == str.charAt(after)) {
                          if (after - before + 1 > maxStrLength) {
                              maxStrLength = after - before + 1;
                              longestStr = str.substring(before, after + 1);
                          }
                          before--;
                          after++;
                      }
                  }
             }
             if (i + 2 <= str.length() - 1 && str.charAt(i) == str.charAt(i + 2)) {
                  before = i;
                  after = i + 2;
                 if (before >= 0 && after >= 0) {
                     while (before >= 0 && after <= str.length() - 1 && str.charAt(before) == str.charAt(after)) {
                         if (after - before + 1 > maxStrLength) {
                             maxStrLength = after - before + 1;
                             longestStr = str.substring(before, after + 1);
                         }
                         before--;
                         after++;
                     }
                 }
              }
        }
        if(longestStr.length() <= 0){
            longestStr = String.valueOf(str.charAt(0));
        }
        return longestStr;
    }
}
