package com.ljm.resource.math.binary;

import com.ljm.resource.math.myList.ListNode;
import com.ljm.resource.math.myList.MyLinkedList;

/**
 * @author liaojiamin
 * @Date:Created in 14:31 2022/2/22
 */
public class ConverListToBinary {

    public static void main(String[] args) {
        ListNode listNode = new ListNode("i", 1);
        for (int i = 6; i < 26; i++) {
            MyLinkedList.addToTail(listNode, i + "_"+ i, i);
        }
        ListNode head = listNode;
        ListNode headNext = listNode.getNext();
        while (headNext != null){
            headNext.setBefore(head);
            head = headNext;
            headNext = headNext.getNext();
        }
        BinaryNode binaryNode = buildTree(listNode, null);
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.printTree(binaryNode);
    }

    public static BinaryNode buildTree(ListNode listNode, ListNode tail){
        if(listNode == tail){
            return null;
        }
        ListNode slow = listNode;
        ListNode fast = listNode;
        while (fast != tail && fast.getNext() != tail){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        BinaryNode binaryNode = new BinaryNode(slow.getValue(), null, null);
        binaryNode.setLeft(buildTree(listNode, slow));
        binaryNode.setRight(buildTree(slow.getNext(), tail));
        return binaryNode;
    }


    /**
     * 快慢指针找链表中间节点
     * */
    public static ListNode getMiddleNode(ListNode listNode){
        if(listNode == null){
            return null;
        }
        ListNode slow = listNode;
        ListNode fast = listNode;
        while (fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        return slow;
    }


    /**
     * 构建二叉排序树
     * */
    public static BinaryNode insertNode(BinaryNode binaryNode, ListNode listNode){
        if(binaryNode == null && listNode == null){
            return null;
        }
        if(binaryNode != null && listNode == null){
            return binaryNode;
        }
        if(binaryNode == null && listNode != null){
            return new BinaryNode(listNode.getValue(), null, null);
        }
        int flag = binaryNode.compareTo(listNode.getValue());
        if(flag >= 0){
            binaryNode.setLeft(insertNode(binaryNode.getLeft(), listNode));
        }else if(flag < 0){
            binaryNode.setRight(insertNode(binaryNode.getRight(), listNode));
        }
        return binaryNode;
    }
}
