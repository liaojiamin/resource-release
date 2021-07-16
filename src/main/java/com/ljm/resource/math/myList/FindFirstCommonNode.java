package com.ljm.resource.math.myList;

import com.ljm.resource.math.myStack.MyStack;

import java.util.Random;

/**
 * 寻找两个单向链表的第一个公共节点
 * 公共节点定义：同一个节点在两个链表中，并不是节点值相同
 *
 * @author liaojiamin
 * @Date:Created in 15:30 2021/6/15
 */
public class FindFirstCommonNode {

    public static void main(String[] args) {
        Random random = new Random();
        ListNode node3 = new ListNode("x", 3);
        ListNode node1 = node3;
        ListNode node2 = node3;

        for (int i = 0; i < 3; i++) {
            Integer number = random.nextInt(100);
            ListNode newNode = new ListNode(number.toString(), number);
            MyLinkedList.addToTail(node1, newNode);
        }


        for (int i = 0; i < 6; i++) {
            Integer number = random.nextInt(100);
            ListNode newNode = new ListNode(number.toString(), number);
            MyLinkedList.addToTail(node2, newNode);
        }


        for (int i = 0; i < 3; i++) {
            Integer number = random.nextInt(100);
            ListNode newNode = new ListNode(number.toString(), number);
            MyLinkedList.addToTail(node1, newNode);
        }



        MyLinkedList.print(node1);
        System.out.println();
        MyLinkedList.print(node2);
        ListNode commonNode = findFirstCommonNodeStack(node1, node2);
        System.out.println();
        if(commonNode != null){
            System.out.println(commonNode.getKey());
        }

        ListNode commonNode2 = findFirstCommonNode(node1, node2);
        if(commonNode2 != null){
            System.out.println(commonNode2.getKey());
        }
    }

    /**
     * 方法二，错位遍历
     * */
    public static ListNode findFirstCommonNode(ListNode node1, ListNode node2){
        if (node1 == null || node2 == null) {
            return null;
        }
        if (node1.getNext() == null && node2.getNext() == null) {
            return node1.equals(node2) ? node1 : null;
        }
        Integer countNode1 = 0;
        ListNode header = node1;
        while (header != null){
            countNode1 ++;
            header = header.getNext();
        }
        Integer countNode2 = 0;
        header = node2;
        while (header != null){
            countNode2 ++;
            header = header.getNext();
        }
        Integer prefix = Math.abs(countNode1 - countNode2);
        ListNode header1 = node1;
        ListNode header2 = node2;
        if(countNode1 > countNode2){
            for (int i =0; i< prefix; i++){
                if(header1 != null){
                    header1 = header1.getNext();
                }

            }
        }else {
            for (int i =0; i< prefix; i++){
                if(header2 != null){
                    header2 = header2.getNext();
                }
            }
        }
        while (header1 != null && header2 != null){
            if(header1.equals(header2)){
                return header1;
            }
            header1 = header1.getNext();
            header2 = header2.getNext();
        }
        return null;
    }

    /**
     * 方法一，栈方法，后进先出
     */
    public static ListNode findFirstCommonNodeStack(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        if (node1.getNext() == null && node2.getNext() == null) {
            return node1.equals(node2) ? node1 : null;
        }
        MyStack stack1 = new MyStack();
        ListNode head = node1;
        while (head != null) {
            stack1.push(head);
            head = head.getNext();
        }
        MyStack stack2 = new MyStack();
        head = node2;
        while (head != null) {
            stack2.push(head);
            head = head.getNext();
        }
        ListNode commonNode = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode popStack1 = (ListNode) stack1.pop();
            ListNode popStack2 = (ListNode) stack2.pop();
            if (popStack1.equals(popStack2)) {
                commonNode = popStack1;
            }
        }
        return commonNode;
    }
}
