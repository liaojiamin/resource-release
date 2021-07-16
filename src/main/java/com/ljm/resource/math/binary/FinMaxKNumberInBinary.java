package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 二叉搜索树中查找第 k 大的节点
 *
 * @author liaojiamin
 * @Date:Created in 10:18 2021/7/16
 */
public class FinMaxKNumberInBinary {
    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        searchTree.printTree(node);
        System.out.println();
        System.out.println(getMaxKNumber(node, 4).getElement());

        System.out.println(getMaxKNumberOver(node, 4).getElement());

    }

    /**
     * 直接从最大的遍历，同时统计遍历节点数，当统计到k个，则是第k个大
     */
    public static BinaryNode getMaxKNumberOver(BinaryNode binaryNode, Integer k) {
        if (binaryNode == null || k < 0) {
            return null;
        }
        BinaryNode baseCount = new BinaryNode(null, null, null);
        baseCount.setCount(0);
        return printOver(binaryNode, baseCount, k);
    }

    /**
     * 右， 中，  左，方式遍历树，与之前树遍历三种都不同
     */
    public static BinaryNode printOver(BinaryNode node, BinaryNode baseCount, Integer k) {
        if(node == null){
            return baseCount;
        }
        baseCount = printOver(node.getRight(), baseCount, k);
        baseCount.setCount(baseCount.getCount() + 1);
        if(baseCount.getCount() == k){
            baseCount = node;
            baseCount.setCount(k);
            return  baseCount;
        }
        return printOver(node.getLeft(), baseCount, k);
    }

    /**
     * 遍历统计，在比较
     */
    public static BinaryNode getMaxKNumber(BinaryNode binaryNode, Integer k) {
        if (binaryNode == null || k < 0) {
            return null;
        }

        if (k == 1) {
            BinaryNode right = binaryNode;
            while (right.getRight() != null) {
                right = right.getRight();
            }
            return right;
        }
        if (k == 0) {
            BinaryNode left = binaryNode;
            while (left.getLeft() != null) {
                left = left.getLeft();
            }
            return left;
        }
        BinaryNode baseCount = new BinaryNode(null, null, null);
        baseCount.setCount(0);
        int rightCount = countNode(binaryNode.getRight(), baseCount).getCount();
        //第k大的在right
        if (rightCount >= k) {
            return getMaxKNumber(binaryNode.getRight(), k);
        }
        //此时root节点是当前第 k大的数据
        if (k - rightCount == 1) {
            return binaryNode;
        }
        //第k大的在left
        if (k - rightCount > 1) {
            return getMaxKNumber(binaryNode.getLeft(), k - rightCount - 1);
        }
        return null;
    }

    /**
     * 统计节点个数
     * */
    public static BinaryNode countNode(BinaryNode binaryNode, BinaryNode baseCount) {
        if (binaryNode == null) {
            return baseCount;
        }
        baseCount.setCount(baseCount.getCount() + 1);
        countNode(binaryNode.getLeft(), baseCount);
        countNode(binaryNode.getRight(), baseCount);
        return baseCount;

    }
}
