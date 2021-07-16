package com.ljm.resource.math.array;

import com.ljm.resource.math.myList.ListNode;
import com.ljm.resource.math.myList.MyLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 找出数组中第一个只出现一次的字符
 * @author liaojiamin
 * @Date:Created in 10:22 2021/6/10
 */
public class FindNotRepeatChar {
    public static void main(String[] args) {
        System.out.println(findNotRepeatCharHashMap("wersdfxvsdfwer"));
        System.out.println(findNotRepeatChar("wersdfxvsdfwer"));
        System.out.println(findNotRepeatCharCompatibleChina("wersdfxxv我sdfwer"));
        System.out.println(findNotRepeatCharCompatibleChinaLinkList("哈哈wersvdfxx我v我sdfwer去"));
    }


    /**
     * 用链表解决
     * */
    public static char findNotRepeatCharCompatibleChinaLinkList(String str) {
        if (str == null) {
            return '\u0000';
        }
        if (str.length() == 1) {
            return str.charAt(0);
        }
        //初始化链表
        ListNode listNode  = new ListNode(String.valueOf(str.charAt(0)), 1);
        char[] target = str.toCharArray();
        for (int i = 1; i < target.length; i++) {
            ListNode header = MyLinkedList.search(listNode, String.valueOf(target[i]));
            if(header != null){
                header.setValue(header.getValue() + 1);
            }else {
                MyLinkedList.addToTail(listNode, String.valueOf(target[i]), 1);
            }
        }
        for (int i = 0; i < target.length; i++) {
            ListNode targetNode = MyLinkedList.search(listNode, String.valueOf(target[i]));
            if(targetNode != null && targetNode.getValue() == 1){
                return target[i];
            }

        }
        return '\u0000';
    }


    /**
     * 异常情况：无法保证中文，英文数据在数组中顺序
     * 包含中文情况
     * */
    public static char findNotRepeatCharCompatibleChina(String str){
        if(str == null){
            return '\u0000';
        }
        if(str.length() == 1){
            return str.charAt(0);
        }
        ListNode listArray[] = new ListNode[str.length()];
        char[] target = str.toCharArray();
        for (int i = 0; i < target.length; i++) {
            Integer position = (new Character(target[i]).hashCode())%str.length();
            if(listArray[position] == null){
                listArray[position] = new ListNode(String.valueOf(target[i]), 1);
            }else {
                ListNode listNode = MyLinkedList.search(listArray[position], String.valueOf(target[i]));
                if(listNode != null){
                    listNode.setValue(listNode.getValue()+1);
                }else {
                    MyLinkedList.addToTail(listArray[position], String.valueOf(target[i]), 1);
                }
            }
        }
        for (int i = 0; i < target.length; i++) {
            Integer position = (new Character(target[i]).hashCode())%str.length();
            ListNode header = listArray[position];
            if(header != null){
               ListNode myNode = MyLinkedList.search(header, String.valueOf(target[i]));
               if(myNode != null && myNode.getValue() == 1){
                   return target[i];
               }
            }
        }
        return '\u0000';
    }

    /**
     * HashMap情况
     * */
    public static char findNotRepeatCharHashMap(String str){
        if(str == null){
            return '\u0000';
        }
        if(str.length() == 1){
            return str.charAt(0);
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] target = str.toCharArray();
        for (int i = 0; i < target.length; i++) {
            Character key = target[i];
            if(map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }
        for (int i = 0; i < target.length; i++) {
            if(map.get(target[i]) == 1){
                return target[i];
            }
        }
        return '\u0000';
    }

    /**
     * 不包含中文情况
     * */
    public static char findNotRepeatChar(String str){
        if(str == null){
            return '\u0000';
        }
        if(str.length() == 1){
            return str.charAt(0);
        }

        int[] charValue = new int[256];
        char[] target = str.toCharArray();
        for (int i = 0; i < target.length; i++) {
            Integer position = new Character(target[i]).hashCode();
            charValue[position] += 1;
        }
        for (int i = 0; i < target.length; i++) {
            Integer position = new Character(target[i]).hashCode();
            if(charValue[position] == 1){
                return target[i];
            }
        }
        return '\u0000';
    }
}
