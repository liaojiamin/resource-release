package com.ljm.resource.math.interview.binary;

/**
 * Created by jiamin5 on 2023/3/14.
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsBalanced {
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

    public class Info {
        int count;
        boolean isBanance;

        Info(int count, boolean isBanance) {
            this.count = count;
            this.isBanance = isBanance;
        }
    }

    public Info chackBalanced(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info left = chackBalanced(root.left);
        Info right = chackBalanced(root.right);
        int count = Math.max(left.count, right.count) + 1;
        boolean isBalanced =  Math.abs(left.count - right.count)  <= 1 && left.isBanance && right.isBanance;
        return new Info(count, isBalanced);
    }

    public boolean isBalanced(TreeNode root) {
        return chackBalanced(root).isBanance;
    }
}
