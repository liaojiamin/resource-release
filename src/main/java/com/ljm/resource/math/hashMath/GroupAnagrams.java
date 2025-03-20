package com.ljm.resource.math.hashMath;

import java.util.*;

/**
 * @author liaojiamin
 * @Date:Created in 11:01 2021/10/14
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if(Objects.isNull(strs) || strs.length == 0){
            return new ArrayList<>();
        }
        List<List<String>> result = new ArrayList<>();
        if(strs.length == 1){
            List<String> strGroup = Arrays.asList(strs[0]);
            result.add(strGroup);
        }
        Map<String, List<String>> keyToResult = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] sortChar = strs[i].toCharArray();
            Arrays.sort(sortChar);
            String sortStr = new String(sortChar);
            keyToResult.putIfAbsent(sortStr, new ArrayList<>());
            keyToResult.get(sortStr).add(strs[i]);
        }
        return new ArrayList<>(keyToResult.values());
    }

    public static void main(String[] args) {
        String[] strs = {"sed","2","ee34","ee34","poiueyvnd","poiuedyvn","3ds","2","3e4e"};
        List<List<String>> groupKey = groupAnagrams(strs);
        for (int i = 0; i < groupKey.size(); i++) {
            System.out.println("group"+ i+": ");
            for (String s : groupKey.get(i)) {
                System.out.print(s + ", ");
            }
            System.out.println();
        }
    }
}
