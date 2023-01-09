package com.ljm.resource.math.myList;

import java.util.Random;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode 2）
 *
 * @author liaojiamin
 * @Date:Created in 15:29 2022/3/1
 */
public class AddTowLinkedNumber {

    public static void main(String[] args) {
        Random random = new Random(10);
        ListNode listNode = new ListNode( 1);
        for (int i = 0; i < 5; i++) {
            MyLinkedList.addToTail(listNode, null, random.nextInt(10));
        }
        ListNode listNode2 = new ListNode( 1);
        for (int i = 0; i < 6; i++) {
            MyLinkedList.addToTail(listNode2, null, random.nextInt(10));
        }
        listNode.setNext(new ListNode(9));
        listNode2.setNext(new ListNode(9));
        MyLinkedList.print(listNode);
        System.out.println();
        MyLinkedList.print(listNode2);
        System.out.println();
        MyLinkedList.print(addTwoNumber(listNode, listNode2));
    }


    public static ListNode addTwoNumber(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head1 = l1;
        int node1Count = 0;
        ListNode head2 = l2;
        int node2Count = 0;
        while (head1 != null) {
            node1Count++;
            head1 = head1.getNext();
        }
        while (head2 != null) {
            node2Count++;
            head2 = head2.getNext();
        }
        ListNode longNode = node1Count > node2Count ? l1 : l2;
        ListNode shortNode = node1Count > node2Count ? l2 : l1;
        int subNode = Math.abs(node1Count - node2Count);
        int position = 0;
        int buffer = 0;
        while (longNode != null) {
            if (position < subNode) {
                longNode = longNode.getNext();
                position++;
                continue;
            }
            int newValue = longNode.getValue() + shortNode.getValue();
            if (buffer > 0) {
                newValue += buffer;
                buffer = 0;
            }
            if (newValue > 10) {
                buffer = 1;
                newValue -= 10;
            }
            longNode.setValue(newValue);
            if(longNode.getNext() == null && buffer > 0){
                longNode.setNext(new ListNode(buffer));
                break;
            }
            longNode = longNode.getNext();
            shortNode = shortNode.getNext();
        }
        return node1Count > node2Count ? l1 : l2;

    }
}
