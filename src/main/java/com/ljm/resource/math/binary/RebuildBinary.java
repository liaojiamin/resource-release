package com.ljm.resource.math.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * 已知前序遍历：1,2,4,7,3,5,6,8 中序遍历：4,7,2,1,5,3,8,6 重建二叉树
 *
 * @author liaojiamin
 * @Date:Created in 17:22 2021/3/8
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RebuildBinary {
    public static BinaryNode construct(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length != inOrder.length || preOrder.length <= 0) {
            System.out.println("Invalid input");
            return null;
        }
        return constructCore(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
    }

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inOrderPositionMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderPositionMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderPositionMap);

    }

    /**
     * pre数组中，[l1,....,r1],in 节点中[l2,....,r2]
     *
     * */
    public TreeNode buildTree(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> inOrderPositionMap) {
        if (l1 > r1) {
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return treeNode;
        }
        int find = inOrderPositionMap.get(pre[l1]);
        treeNode.left = buildTree(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, inOrderPositionMap);
        treeNode.right = buildTree(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, inOrderPositionMap);
        return treeNode;
    }

    public static BinaryNode constructCore(int[] preOrder, int[] inOrder,
                                           int preStart, int preEnd,
                                           int inStart, int inEnd) {
        int rootValue = preOrder[preStart];
        BinaryNode rootNode = new BinaryNode(rootValue, null, null);
        //整棵树只有一个root节点的清空
        if (preStart == preEnd) {
            if (inStart == inEnd && preOrder[preStart] == inOrder[inStart]) {
                return rootNode;
            } else {
                System.out.println("Invalid input");
                return null;
            }
        }
        //找到root节点在 中序遍历中位置
        int rootInOrderIndex = inStart;
        while (rootInOrderIndex <= inEnd && inOrder[rootInOrderIndex] != rootValue) {
            rootInOrderIndex++;
        }
        //中序遍历中没有找到root节点
        if (rootInOrderIndex == inEnd && inOrder[rootInOrderIndex] != rootValue) {
            System.out.println("Invalid input");
            return null;
        }
        int leftLength = rootInOrderIndex - inStart;

        //没有左子树情况start = end
        if (leftLength > 0) {
            int leftInOrderStart = inStart;
            int leftInOrderEnd = inStart + leftLength - 1;

            int leftPreOrderStart = preStart + 1;
            int leftPreOrderEnd = preStart + leftLength;
            rootNode.setLeft(constructCore(preOrder, inOrder, leftPreOrderStart, leftPreOrderEnd, leftInOrderStart, leftInOrderEnd));
        }
        //存在右节点
        if (leftLength < preEnd - preStart) {
            int rightInOrderStart = rootInOrderIndex + 1;
            int rightInOrderEnd = inEnd;

            int rightPreOrderStart = preStart + 1 + leftLength;
            int rightPreOrderEnd = preEnd;
            rootNode.setRight(constructCore(preOrder, inOrder, rightPreOrderStart, rightPreOrderEnd, rightInOrderStart, rightInOrderEnd));
        }
        return rootNode;
    }

    public static void main(String[] args) {
        int preOrder[] = {1, 2, 4, 7, 3, 5, 6, 8};
        int inOrder[] = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryNode binaryNode = construct(preOrder, inOrder);
        PostfixExTOTreeEx.printMiddleFirstTree(binaryNode);
    }
}
