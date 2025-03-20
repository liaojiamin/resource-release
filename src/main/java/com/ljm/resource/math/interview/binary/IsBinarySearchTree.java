package com.ljm.resource.math.interview.binary;

/**
 * Created by jiamin5 on 2023/3/14.
 */
public class IsBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Info{
        int min;
        int max;
        boolean isBinarySearchTree;
        public Info(int min, int max, boolean isBinarySearchTree){
            this.min = min;
            this.max = max;
            this.isBinarySearchTree = isBinarySearchTree;
        }
    }

    public boolean checkBinarySearchTree(TreeNode root, long min, long max){
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >= max){
            return false;
        }
        return checkBinarySearchTree(root.left, min, root.val) && checkBinarySearchTree(root.right, root.val, max);
    }


    public Info checkBinarySearchTree(TreeNode root){
        if(root == null){
            return  null;
        }
        Info left = checkBinarySearchTree(root.left);
        Info right = checkBinarySearchTree(root.right);
        int min = root.val;
        int max = root.val;
        if(left != null){
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
        }
        if(right != null){
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }
        boolean isBinarySearchTree = true;
        if(left != null && !left.isBinarySearchTree){
            isBinarySearchTree = false;
        }
        if(right != null && !right.isBinarySearchTree){
            isBinarySearchTree = false;
        }
        boolean leftCheck = left == null ? true : left.max < root.val;
        boolean rightCheck = right == null ? true : right.min > root.val;
        if(!leftCheck || !rightCheck){
            isBinarySearchTree = false;
        }
        return new Info(min, max, isBinarySearchTree);
    }

    public boolean isValidBST(TreeNode root){
//        return checkBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return checkBinarySearchTree(root).isBinarySearchTree;
    }
}
