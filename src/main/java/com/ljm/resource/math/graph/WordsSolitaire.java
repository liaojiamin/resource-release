package com.ljm.resource.math.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 图论，词梯实现
 * @author liaojiamin
 * @Date:Created in 14:19 2021/1/4
 */
public class WordsSolitaire {
    public static final List<String> basicWords = new ArrayList<>();

    /**
     * 比较是否只有一个字符不同的单词
     */
    public static boolean oneCharOff(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }
        if (word1 == word2) {
            return false;
        }
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (word1.substring(i + 1, word1.length()) != word2.substring(i + 1, word2.length())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 比较是否只添加/删除一个字符得到
     * */
    public static boolean oneCharAdd(String word1, String word2){
        if(word1 == null || word2 == null || Math.abs(word1.length() - word2.length()) != 1){
            return false;
        }
        String largeWord = word1.length() > word2.length() ? word2 : word1;
        String shortWord = word1.length() > word2.length() ? word1 : word2;
        Integer count = 0;
        Integer largePis = 0;
        for (int i = 0; i < shortWord.length(); i++) {
            if(shortWord.charAt(i) != largeWord.charAt(largePis)){
                count ++;
                if(count > 1){
                    return false;
                }
                if(shortWord.charAt(i) != largeWord.charAt(++largePis)){
                    return false;
                }
            }
            largePis++;
        }
        return true;

    }

    /**
     * 修改Map中数组对象元素
     */
    public static void update(Map<String, List<String>> map, String key, String value) {
        List<String> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        list.add(value);
    }

    /**
     * 方法一： 时间复杂度 O(N^2)
     * 处理basicWords 数组，最终 产出Map<String, List<String>>
     * 获取以单词为key， 值修改一个字符可组成的新的单词为数组的value值的Map对象
     * base数组基数89000， 运行75秒
     */
    public static Map<String, List<String>> computeAdjacentWords(List<String> basicWords) {
        Map<String, List<String>> adjMap = new HashMap<>();
        for (int i = 0; i < basicWords.size(); i++) {
            for (int j = i + 1; j < basicWords.size(); j++) {
                if (oneCharOff(basicWords.get(i), basicWords.get(j)) || oneCharAdd(basicWords.get(i), basicWords.get(j))) {
                    update(adjMap, basicWords.get(i), basicWords.get(j));
                    update(adjMap, basicWords.get(j), basicWords.get(i));
                }
            }
        }
        return adjMap;
    }

    /**
     * 方法二
     * 处理basicWords 数组，最终 产出Map<String, List<String>>
     * 获取以单词为key， 值修改一个字符可组成的新的单词为数组的value值的Map对象
     * base数组基数89000， 运行16秒
     */
    public static Map<String, List<String>> computeAdjacentWords_v1(List<String> basicWords) {
        Map<String, List<String>> adjMap = new HashMap<>();
        Map<String, List<String>> lengthMap = new HashMap<>();
        //先分类,按字符串长度分类
        for (String basicWord : basicWords) {
            update(lengthMap, String.valueOf(basicWord.length()), basicWord);
        }
        for (List<String> strings : lengthMap.values()) {
            for (int i = 0; i < strings.size(); i++) {
                for (int j = i + 1; j < strings.size(); j++) {
                    if (oneCharOff(strings.get(i), strings.get(j)) || oneCharAdd(strings.get(i), strings.get(j))) {
                        update(adjMap, strings.get(i), strings.get(j));
                        update(adjMap, strings.get(j), strings.get(i));
                    }
                }
            }
        }
        return adjMap;
    }

    /**
     * 有权重图最短路径算法 实现词梯
     * @param first 词梯开始单词
     * @Param second 词梯结束单词
     */
    public static List<String> findChain(List<String> baseWords, String first, String second) {
        //处理基础数据，得到对应 权重为1 的图基本数据结构 用Map表示的邻接表
        Map<String, List<String>> adjacentWords = computeAdjacentWords_v1(baseWords);
        LinkedList<String> q = new LinkedList<>();
        Map<String, String> previousWords = new HashMap<>();
        q.add(first);
        while (!q.isEmpty()) {
            String current = q.removeFirst();
            List<String> currentArray = adjacentWords.get(current);
            if (currentArray != null && currentArray.size() > 0) {
                for (String adjWord : currentArray) {
                    //==null 相当于 之前know 的节点属性为以及遍历过，则无需在处理避免 有圈图
                    if(previousWords.get(adjWord) == null){
                        //通过map，一层及一层及剥离，最终得到 second--X--Y--Z--first的map链
                        previousWords.put(adjWord, current);
                        q.add(adjWord);
                    }
                }
            }
        }
        previousWords.put(first, null);
        return getChainFromPreviousMap(previousWords, first, second);
    }


    public static List<String> getChainFromPreviousMap( Map<String, String> previousWords, String first, String second){
        LinkedList<String> q = null;
        if(previousWords.get(second) != null){
            q = new LinkedList<>();
            for(String str = second; str != null ; str = previousWords.get(str)){
                q.addFirst(str);
            }
        }
        return q;
    }
}
