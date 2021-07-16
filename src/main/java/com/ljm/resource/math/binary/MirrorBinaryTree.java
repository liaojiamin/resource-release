package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 获取二叉树的镜像
 * @author liaojiamin
 * @Date:Created in 17:36 2021/3/31
 */
public class MirrorBinaryTree {

    public static BinaryNode mirrorTree(BinaryNode root){
        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return root;
        }
        BinaryNode treeTemp = root.getRight();
        root.setRight(root.getLeft());
        root.setLeft(treeTemp);
        if(root.getRight() != null){
            mirrorTree(root.getRight());
        }
        if(root.getLeft() != null){
            mirrorTree(root.getLeft());
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree tree1 = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node = tree1.insert(random.nextInt(100), node);
        }
        tree1.printTree(node);
        System.out.println("----------------------mirror------------");
        mirrorTree(node);
        tree1.printTree(node);
    }
}
