package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 二叉查找树转顺序排列的双向链表
 *
 * @author liaojiamin
 * @Date:Created in 14:27 2021/5/19
 */
public class ConvertBinaryToList {

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        printMiddle(node);
        BinaryNode doubleLink = convertBinary(node);
        BinaryNode headNode = doubleLink;
        while (headNode.getLeft() != null) {
            headNode = headNode.getLeft();
        }
        while (headNode.getRight() != null) {
            System.out.print("value: " + headNode.getElement());
            System.out.print("left: " + (headNode.getLeft() != null ? headNode.getLeft().getElement() : ""));
            System.out.print("right: " + (headNode.getRight() != null ? headNode.getRight().getElement() : ""));
            System.out.println();
            headNode = headNode.getRight();
            if(headNode.getRight() == null ){
                System.out.print("value: " + headNode.getElement());
                System.out.print("left: " + (headNode.getLeft() != null ? headNode.getLeft().getElement() : ""));
                System.out.print("right: " + (headNode.getRight() != null ? headNode.getRight().getElement() : ""));
            }
        }
    }

    /**
     * 二叉查找树转双向链表，顺序
     * 递归处理后，找出最左节点
     */
    public static BinaryNode convertBinary(BinaryNode root) {
        BinaryNode pLastNodeInList = null;
        pLastNodeInList = convertNode(root, pLastNodeInList);
        BinaryNode pHeadOfLast = pLastNodeInList;
        while (pHeadOfLast != null && pHeadOfLast.getLeft() != null) {
            pHeadOfLast = pHeadOfLast.getLeft();
        }
        return pHeadOfLast;
    }

    public static BinaryNode convertNode(BinaryNode node, BinaryNode pLastNodeInList) {
        if (node == null) {
            return pLastNodeInList;
        }
        BinaryNode current = node;
        if (current.getLeft() != null) {
            //递归左节点
            pLastNodeInList = convertNode(current.getLeft(), pLastNodeInList);
        }
        //此时node节点是根，将左子树的最大节点pLastNodeInList 设置为根的left节点
        current.setLeft(pLastNodeInList);
        if (pLastNodeInList != null) {
            //此时的pLastNodeInList 是左子树的最大节点，他的right应该是root，也就是当前的current
            pLastNodeInList.setRight(current);
        }
        //此时左节点干完，左节点的最大节点是当前root也就是current
        pLastNodeInList = current;
        //递归右节点
        if (current.getRight() != null) {
            pLastNodeInList = convertNode(current.getRight(), pLastNodeInList);
        }
        return pLastNodeInList;
    }

    /**
     * 中序遍历
     */
    public static void printMiddle(BinaryNode root) {
        if (root == null || root.getElement() == null) {
            return;
        }
        printMiddle(root.getLeft());
        System.out.println(root.getElement());
        printMiddle(root.getRight());
    }

}




























