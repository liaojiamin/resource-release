package com.ljm.resource.math.interview.binary;

/**
 * Created by jiamin5 on 2023/3/14.
 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/symmetric-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class IsSymmetric {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    public boolean isMirror(TreeNode node1, TreeNode node2){
        if(node1 == null ^ node2 == null){
            return false;
        }
        if(node1 == null && node2 == null){
            return true;
        }
        return node1.val == node2.val
                && isMirror(node1.left, node2.right)
                && isMirror(node1.right, node2.left);
    }

}
