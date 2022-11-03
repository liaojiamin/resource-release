package com.ljm.resource.math;

import com.ljm.resource.math.binary.BinaryNode;
import com.ljm.resource.math.binary.BinarySearchTree;
import com.ljm.resource.math.myList.ListNode;
import com.ljm.resource.math.myList.MyLinkedList;

import java.awt.*;

/**
 * Created by jiamin5 on 2022/10/21.
 */
public class ConverListToBinaryTest {
    public static void main(String[] args) {
        ListNode doubleLineList = buildDoubleLineLink(10);
        BinaryNode binaryTree = buildBinaryTree(doubleLineList, null);
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.printTree(binaryTree);
    }


    /**
     * 构造双向链表
     * */
    public static ListNode buildDoubleLineLink(int nodeCount){
        ListNode headNode = new ListNode("i", 1);
        for (int i = 0; i < nodeCount; i++) {
            MyLinkedList.addToTail(headNode, i+"_"+i, i);
        }
        ListNode pNode = headNode;
        ListNode pNextNode = pNode.getNext();
        while (pNextNode != null){
            pNextNode.setBefore(pNode);
            pNode = pNextNode;
            pNextNode = pNextNode.getNext();
        }
        return headNode;
    }


    /**
     * 递归构建二叉搜索树
     * */
    public static BinaryNode buildBinaryTree(ListNode head, ListNode tail){
        if(head == tail){
            return null;
        }
        ListNode fast = head;
        ListNode low = head;
        while (fast != tail && fast.getNext() != tail){
            low = low.getNext();
            fast = fast.getNext().getNext();
        }
        BinaryNode binaryNode = new BinaryNode(low.getValue(), null, null);
        binaryNode.setLeft(buildBinaryTree(head, low));
        binaryNode.setRight(buildBinaryTree(low.getNext(), tail));
        return binaryNode;




    }
}
