package com.ljm.resource.math.myList;

import java.util.HashMap;
import java.util.Map;

/**
 * 单向链表中删除相同的数字
 * @author liaojiamin
 * @Date:Created in 10:01 2021/7/6
 */
public class FindCommonNumberListInSortList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode("1",1);
        for (int i=2;i<30;i++){
            MyLinkedList.addToTail(listNode, i+"",i);
            if(i%2==0){
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
            }
        }
//        MyLinkedList.print(listNode);
//        findCommonNode(listNode);
//        System.out.println();
//        MyLinkedList.print(listNode);

        System.out.println();
        ListNode newNode = findCommonNodeInNoSort(listNode);
        MyLinkedList.print(newNode);
    }

    /**
     * 无序单向链表中去除相同值的节点
     * */
    public static ListNode findCommonNodeInNoSort(ListNode listNode){
        if(listNode == null || listNode.getNext() == null){
            return listNode;
        }
        ListNode headFirst = listNode;
        Map<String, Integer> listNodeMap = new HashMap<>();
        ListNode newList = null;
        ListNode newHead = newList;
        while (headFirst != null){
            if(!listNodeMap.containsKey(headFirst.getKey())){
                if(newHead != null){
                    newHead.setNext(headFirst);
                    newHead = newHead.getNext();
                }else {
                    newList = headFirst;
                    newHead = newList;
                }
                listNodeMap.put(headFirst.getKey(), headFirst.getValue());
            }
            headFirst = headFirst.getNext();
        }
        return newList;
    }

    /**
     * 双指针去除顺序链表中相同值的节点
     * 时间复杂度O(n)，空间复杂度O(1)
     * */
    public static ListNode findCommonNode(ListNode listNode){
        if(listNode == null || listNode.getNext() == null){
            return listNode;
        }
        ListNode headFirst = listNode;
        ListNode headSecond = listNode.getNext();
        while (headSecond != null){
            if(headSecond.compareTo(headFirst) == 0){
                headFirst.setNext(headSecond.getNext());
            }else {
                headFirst = headSecond;
            }
            headSecond = headSecond.getNext();
        }
        return listNode;
    }
}
