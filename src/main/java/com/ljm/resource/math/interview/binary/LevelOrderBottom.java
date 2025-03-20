package com.ljm.resource.math.interview.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiamin5 on 2023/3/14.
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 输入：root = [3,9,20,null,null,15,7]
 输出：[[15,7],[9,20],[3]]

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrderBottom {
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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null){
            return null;
        }
        LinkedList<List<Integer>> lists = new LinkedList<>();
        LinkedList<TreeNode> treeList = new LinkedList<>();
        treeList.addFirst(root);
        while (!treeList.isEmpty()){
            Integer temp = treeList.size();
            List<Integer> treeNode = new ArrayList<>();
            for (;temp >0;temp--){
                TreeNode thisNode = treeList.removeLast();
                treeNode.add(thisNode.val);
                if(thisNode.left != null){
                    treeList.addFirst(thisNode.left);
                }
                if(thisNode.right != null){
                    treeList.addFirst(thisNode.right);
                }
            }
            lists.addFirst(treeNode);
        }
        return lists;
    }
}
