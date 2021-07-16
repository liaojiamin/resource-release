package com.ljm.resource.math.binary;

import com.ljm.resource.math.myList.ListNode;
import com.ljm.resource.math.myList.MyLinkedList;
import com.ljm.resource.math.myStack.MyStack;

import java.util.Random;
import java.util.TreeSet;

/**
 * 输入两个树的节点node1， node2，找到他们最低公共祖先.
 * 最近公共祖先的定义为：对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *
 * @author liaojiamin
 * @Date:Created in 16:31 2021/7/9
 */
public class FindCommonNode {

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        BinaryNode node1 = new BinaryNode(29, null, null);
        node = searchTree.insert(node1, node);
        BinaryNode node2 = new BinaryNode(45, null, null);
        node = searchTree.insert(node2, node);
        BinaryNode result = findBinarySearchTree(node, node1, node2);
        System.out.println(result.getElement());
        BinaryNode result2 = findBinaryTree(node, node1, node2);
        System.out.println(result2.getElement());
        BinaryNode result3 = buildBinaryLink(node, node1, node2);
        System.out.println(result3.getElement());

    }

    /**
     * 构造两个单向链表
     */
    public static BinaryNode buildBinaryLink(BinaryNode tree, BinaryNode node1, BinaryNode node2) {
        if (tree == null || node1 == null || node2 == null) {
            return null;
        }

        buildListNode(tree, node1, node2);
        ListNode node1List = node1.getLinkedList();
        ListNode node2List = node2.getLinkedList();
        MyStack<BinaryNode> stack1 = new MyStack<>();
        MyStack<BinaryNode> stack2 = new MyStack<>();
        while (node1List != null) {
            if (node1List.getBinaryNode() != null) {
                stack1.push(node1List.getBinaryNode());
            }
            node1List = node1List.getNext();
        }
        while (node2List != null) {
            if (node2List.getBinaryNode() != null) {
                stack2.push(node2List.getBinaryNode());
            }
            node2List = node2List.getNext();
        }
        //去掉node1， node2 节点，可能出现单链情况的树，也就是node1， node2，同时出现在一个链中
        BinaryNode stackNode1 = stack1.pop();
        while (!stack1.isEmpty() && (stackNode1.compareTo(node1) == 0 || stackNode1.compareTo(node2) == 0)) {
            stackNode1 = stack1.pop();
        }
        BinaryNode stackNode2 = stack2.pop();
        while (!stack2.isEmpty() && (stackNode2.compareTo(node1) == 0 || stackNode2.compareTo(node2) == 0)){
            stackNode2 = stack2.pop();
        }
        do {
            if(stackNode1.compareTo(stackNode2) == 0){
                return stackNode1;
            }
            if(stack1.size() > stack2.size() && !stack1.isEmpty()){
                stackNode1 = stack1.pop();
            }else if(stack1.size() < stack2.size() && !stack2.isEmpty()){
                stackNode2 = stack2.pop();
            }else if(!stack2.isEmpty() && !stack1.isEmpty()){
                stackNode1 = stack1.pop();
                stackNode2 = stack2.pop();
            }else {
                return  null;
            }
        }while (true);
    }

    /**
     * 构造节点路径
     * */
    public static void buildListNode(BinaryNode tree, BinaryNode node1, BinaryNode node2) {
        if (tree == null) {
            return;
        }
        //初始化根节点路径
        if (tree.getLinkedList() == null) {
            ListNode treeList = new ListNode(tree);
            tree.setLinkedList(treeList);
        }
        if (tree.getLeft() != null) {
            //将父节点路径复制到子节点
            ListNode leftList = new ListNode();
            ListNode header = tree.getLinkedList();
            while (header != null) {
                ListNode newNode = new ListNode(header.getBinaryNode());
                MyLinkedList.addToTail(leftList, newNode);
                header = header.getNext();
            }
            //添加子节点本身，得到节点最终路径
            MyLinkedList.addToTail(leftList, new ListNode(tree.getLeft()));
            tree.getLeft().setLinkedList(leftList);
        }
        if (tree.getRight() != null) {
            //将父节点路径复制到子节点
            ListNode rightList = new ListNode();
            ListNode header = tree.getLinkedList();
            while (header != null) {
                ListNode newNode = new ListNode(header.getBinaryNode());
                MyLinkedList.addToTail(rightList, newNode);
                header = header.getNext();
            }
            //添加子节点本身，得到节点最终路径
            MyLinkedList.addToTail(rightList, new ListNode(tree.getRight()));
            tree.getRight().setLinkedList(rightList);
        }
        //当输入节点路径都不为空，则表示已经查找完毕
        if (node1.getLinkedList() != null && node2.getLinkedList() != null) {
            return;
        }
        buildListNode(tree.getLeft(), node1, node2);
        buildListNode(tree.getRight(), node1, node2);
    }

    /**
     * 非二叉排序树
     */
    public static BinaryNode findBinaryTree(BinaryNode tree, BinaryNode node1, BinaryNode node2) {
        if (tree == null || node1 == null || node2 == null) {
            return null;
        }
        //递归判断修改状态，所以每次都先初始化数量为0
        node1.setCount(0);
        node2.setCount(0);
        boolean left = validateNode(tree.getLeft(), node1, node2);
        node1.setCount(0);
        node2.setCount(0);
        boolean right = left ? false : validateNode(tree.getRight(), node1, node2);
        if (!left && !right) {
            return tree;
        }
        if (left) {
            //特殊情况二叉树为单条链的情况
            if (tree.getLeft() == node1 || tree.getLeft() == node2) {
                return tree;
            }
            return findBinaryTree(tree.getLeft(), node1, node2);
        }
        if (right) {
            if (tree.getRight() == node1 || tree.getRight() == node2) {
                return tree;
            }
            return findBinaryTree(tree.getRight(), node1, node2);
        }
        return null;
    }

    /**
     * 判断节点是否在二叉树中
     */
    public static boolean validateNode(BinaryNode tree, BinaryNode node1, BinaryNode node2) {
        if (tree == null) {
            return false;
        }
        if (tree.compareTo(node1) == 0) {
            node1.setCount(2);
        }
        if (tree.compareTo(node2) == 0) {
            node2.setCount(2);
        }
        if (node1.getCount() == 2 && node2.getCount() == 2) {
            return true;
        }
        boolean leftIn = validateNode(tree.getLeft(), node1, node2);
        boolean rightIn = validateNode(tree.getRight(), node1, node2);
        return leftIn || rightIn;
    }


    /**
     * 二叉排序树解法
     */
    public static BinaryNode findBinarySearchTree(BinaryNode tree, BinaryNode node1, BinaryNode node2) {
        if (tree == null || node1 == null || node2 == null) {
            return null;
        }
        // node1< tree < node2
        if (node1.compareTo(node2) < 0 && tree.compareTo(node1) > 0 && tree.compareTo(node2) < 0) {
            return tree;
        }
        // node2< tree < node1
        if (node1.compareTo(node2) > 0 && tree.compareTo(node1) < 0 && tree.compareTo(node2) > 0) {
            return tree;
        }
        if (tree.compareTo(node1) > 0 & tree.compareTo(node2) > 0) {
            if (tree.getLeft() == node1 || tree.getLeft() == node2) {
                return tree;
            }
            return findBinarySearchTree(tree.getLeft(), node1, node2);
        }
        if (tree.compareTo(node1) < 0 & tree.compareTo(node2) < 0) {
            if (tree.getRight() == node1 || tree.getRight() == node2) {
                return tree;
            }
            return findBinarySearchTree(tree.getRight(), node1, node2);
        }
        return null;
    }
}
