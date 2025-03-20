package com.ljm.resource.math.interview.mylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamin5 on 2023/3/23.
 */
public class DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 指针法，删除数组中指定值的节点
     * */
    public static Node removeValue(Node head, int num) {
        if(head == null){
            return head;
        }
        while (head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node next = head;
        while (next != null){
            if(next.value != num){
                pre = next;
                next = next.next;
            }else {
                pre.next = next.next;
                next = next.next;
            }
        }
        return head;
    }

    /**
     * 额外空间法，删除数组中指定值的节点
     * */
    public static Node removeValue_1(Node head, int num){
        if(head == null){
            return head;
        }
        List<Node> nodeList = new ArrayList<>();
        while (head != null){
            if(head.value != num){
                nodeList.add(head);
            }
            head = head.next;
        }
        if(nodeList .size() <= 0){
            return null;
        }
        Node newHead = nodeList.get(0);
        Node position = newHead;
        for (int i = 1; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            node.next = null;
            position.next = node;
            position = node;
        }
        return newHead;
    }


    // for test
    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    /**
     * 遍历链表
     * */
    public static void printListNode(Node node){
        if(node == null){
            return;
        }
        Node head = node;
        while (head != null){
            System.out.print(head.value + ", ");
            head = head.next;
        }
    }

    /**
     * 对比两个链表释放完全一样
     * */
    public static boolean comparator(Node node1, Node node2){
        if(!(node1 != null ^ node2 != null)){
            return false;
        }
        while (true){
            if(node1 == null && node2 == null){
                return true;
            }
            if(node1.value != node2.value){
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
    }



    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
//            printListNode(node1);
            if(node1 == null){
                continue;
            }
            int removeNum = node1.value;
//            System.out.println();
            Node funOneNode = removeValue(node1, removeNum);
            Node funTwoNode = removeValue_1(node1, removeNum);
            if(comparator(funOneNode, funTwoNode)){
                System.out.println("error begin");
                System.out.println(removeNum);
                printListNode(funOneNode);
                printListNode(funTwoNode);
                System.out.println("error end");
                return;
            }
        }
    }
}
