package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 求二叉树的深度
 * @author liaojiamin
 * @Date:Created in 17:43 2021/6/24
 */
public class BinaryDepth {

    public static void main(String[] args) {
        BinaryNode node1 = new BinaryNode(null, null, null);
        BinarySearchTree tree1 = new BinarySearchTree();
        Random random = new Random();
        int[] array = new int[]{1,27,37,19,514,216,118,320,426,228};
        for (int i = 0; i < array.length; i++) {
            node1 = tree1.insert(Integer.valueOf(random.nextInt(20)), node1);
        }
        System.out.println(depthBinary(node1));
        System.out.println(validateBalancedTree(node1));
        System.out.println(validateBalancedTreeHeight(node1));
        tree1.printTree(node1);
    }


    /**
     * 一棵二叉树中所有节点对应的左右子树高度差不超过1 ，那么说明这棵树是平衡二叉树
     * 递归判断是否平衡树
     * */
    public static boolean validateBalancedTree(BinaryNode tree){
        if(tree == null){
            return true;
        }
        int left = depthBinary(tree.getLeft());
        int right = depthBinary(tree.getRight());
        int diff = Math.abs(left - right);
        if(diff > 1){
            return false;
        }
        return validateBalancedTree(tree.getLeft()) && validateBalancedTree(tree.getRight());
    }

    /**
     * 直接后序遍历，同时标记深度
     * */
    public static boolean validateBalancedTreeHeight(BinaryNode tree){
        if(tree == null){
            return true;
        }
        boolean left = validateBalancedTreeHeight(tree.getLeft());
        boolean right = validateBalancedTreeHeight(tree.getRight());
        int leftHeight = tree.getLeft()!= null ? tree.getLeft().getHeight(): 0;
        int rightHeight = tree.getRight() != null ? tree.getRight().getHeight() : 0;
        tree.setHeight(1 + Math.max(leftHeight, rightHeight));
        if(left && right){
            int diff = Math.abs(leftHeight - rightHeight);
            if(diff > 1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }


    /**
     * 递归方式求解，
     *  左节点为空则，左高度为left = 0，总高度为 left + 1
     *  右节点为空则，右高度为right = 0，总高度为 right + 1
     *  递归到子节点，赋值left= 0，right = 0，接着一层一层递归上到根节点。
     * */
    public static Integer depthBinary(BinaryNode tree){
        if(tree == null){
            return 0;
        }
        int left = depthBinary(tree.getLeft());
        int right = depthBinary(tree.getRight());
        return left > right ? left + 1 : right + 1;
    }
}
