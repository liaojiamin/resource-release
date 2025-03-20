package com.ljm.resource.math.interview.binary;

/**
 * Created by jiamin5 on 2023/3/14.
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

 输入：p = [1,2,3], q = [1,2,3]
 输出：true

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/same-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class IsSameTree {
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null ^ q == null){
            return false;
        }
        if(p == null && q == null){
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
    }
}
