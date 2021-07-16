package com.ljm.resource.math.binary;

import java.util.Random;

/**
 *  判断 A树中是否包含B树
 *  @author liaojiamin
 * @Date:Created in 16:43 2021/3/30
 */
public class BinaryNodeComparable {

    /**
     * 递归方式遍历树
     * */
    public static boolean isComparable(BinaryNode tree1, BinaryNode tree2){
        if(tree1 == null || tree2 == null){
            return false;
        }
        boolean result = false;
        if(tree1.compareTo(tree2.getElement()) == 0){
            result = tree1HaveTree2(tree1, tree2);
        }
        if(!result){
            result = isComparable(tree1.getLeft(), tree2);
        }
        if(!result){
            result = isComparable(tree1.getRight(), tree2);
        }
        return result;
    }

    public static boolean tree1HaveTree2(BinaryNode tree1, BinaryNode tree2){
        //t2 遍历完了并且都一致，则存在包含
        if(tree2 == null){
            return true;
        }
        //t2 不为空，t1 为空 ，则不存在包含
        if(tree1 == null){
            return false;
        }
        if(tree1.compareTo(tree2.getElement()) != 0){
            return false;
        }
        return tree1HaveTree2(tree1.getLeft(), tree2.getLeft())
                && tree1HaveTree2(tree1.getRight(), tree2.getRight());
    }

    public static void main(String[] args) {
        BinaryNode node1 = new BinaryNode(null, null, null);
        BinarySearchTree tree1 = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node1 = tree1.insert(Integer.valueOf(i), node1);
        }
        tree1.printTree(node1);

        System.out.println("-------------");
        BinaryNode node2 = new BinaryNode(null, null, null);
        BinarySearchTree tree2 = new BinarySearchTree();
        for (int i = 0; i < 3; i++) {
            node2 = tree2.insert(Integer.valueOf(i), node2);
        }
        tree2.printTree(node2);
        System.out.println(isComparable(node1, node2));

    }
}
