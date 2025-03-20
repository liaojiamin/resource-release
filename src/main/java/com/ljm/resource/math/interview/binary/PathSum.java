package com.ljm.resource.math.interview.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamin5 on 2023/3/14.
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PathSum {
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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> sumList = new ArrayList<>();
        if(root == null){
            return sumList;
        }
        List<Integer> path = new ArrayList<>();
        process(root, path, 0, sumList, targetSum);
        return sumList;
    }

    public void process(TreeNode root, List<Integer> path, int preSum, List<List<Integer>> sumList, int target) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == target) {
                path.add(root.val);
                sumList.add(copyList(path));
                //回朔到上一个节点
                path.remove(path.size() - 1);
            }
            return;
        }
        preSum += root.val;
        path.add(root.val);
        if (root.left != null) {
            process(root.left, path, preSum, sumList, target);
        }
        if(root.right != null){
            process(root.right, path, preSum, sumList, target);
        }
        //回朔到上一个节点
        path.remove(path.size() -1);

    }

    public List<Integer> copyList(List<Integer> path){
        List<Integer> myCopy = new ArrayList<>();
        for (Integer integer : path) {
            myCopy.add(integer);
        }
        return myCopy;
    }
}
