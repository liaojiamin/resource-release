package com.ljm.resource.math.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中查找和为n 的路径
 *
 * @author liaojiamin
 * @Date:Created in 10:51 2021/5/17
 */
public class FindPathInBinary {
    public static boolean findPath(BinaryNode binaryNode, Integer target) {
        List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(binaryNode.getElement().toString()));
        Integer count = Integer.valueOf(binaryNode.getElement().toString());
        return findPath(binaryNode, list, count, target);
    }

    public static boolean findPath(BinaryNode binaryNode, List<Integer> beforePath, Integer beforeCount, Integer target) {
        if (binaryNode.getLeft() == null && binaryNode.getRight() == null) {
            printPath(beforePath);
            System.out.println("beforeCount:" + beforeCount);
            if(beforeCount == target){
                System.out.print("have path:");
                printPath(beforePath);
                System.out.println();
            }
            return false;
        }
        if (binaryNode.getLeft() != null) {
            BinaryNode left = binaryNode.getLeft();
            Integer count = beforeCount + Integer.valueOf(left.getElement().toString());
            List<Integer> list = new ArrayList<>();
            list.addAll(beforePath);
            list.add(Integer.valueOf(left.getElement().toString()));
            findPath(binaryNode.getLeft(), list, count, target);
        }
        if(binaryNode.getRight() != null){
            BinaryNode right = binaryNode.getRight();
            Integer count =  beforeCount + Integer.valueOf(right.getElement().toString());
            List<Integer> list = new ArrayList<>();
            list.addAll(beforePath);
            list.add(Integer.valueOf(right.getElement().toString()));
            findPath(binaryNode.getRight(), list, count, target);
        }
        return false;
    }

    public static void printPath(List<Integer> objectList) {
        for (Object o : objectList) {
            System.out.print(o);
            System.out.print(",");
        }
    }

    public static void main(String[] args) {
//        Integer arr[] = new Integer[]{44,94,92,23,42,13,7,76,70,40,78,28,78,36,14,53,10,91,36,15};
        Integer arr[] = new Integer[]{10,5,4,7,12};
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            node = searchTree.insert(num, node);
            System.out.print(num+",");
        }
        System.out.println();
        System.out.println();
        findPath(node, 22);
    }

}
