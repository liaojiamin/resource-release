package com.ljm.resource.math.myList;

import java.util.Random;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 * Created by jiamin5 on 2022/12/6.
 */
public class MergeTwoSortList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode( 0);
        for (int i = 2; i < 5; i++) {
            MyLinkedList.addToTail(listNode, null, i+4);
        }
        ListNode listNode2 = new ListNode( 1);
        for (int i = 7; i < 20; i++) {
            MyLinkedList.addToTail(listNode2, null, i-1);
        }
        MyLinkedList.print(listNode);
        System.out.println();
        MyLinkedList.print(listNode2);
        System.out.println();
        MyLinkedList.print(mergeList(listNode, listNode2));
    }

    /**
     * 递归
     * */
    public static ListNode mergeList(ListNode l1, ListNode l2){
        if(l1 == null && l2 == null){
            return null;
        }
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.getValue() < l2.getValue()){
            l1.setNext(mergeList(l1.getNext(), l2));
            return l1;
        }
        l2.setNext(mergeList(l1, l2.getNext()));
        return l2;
    }
}
