package com.ljm.resource.math.binary;

import com.ljm.resource.math.array.BinarySearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数n，生成并返回所有N个节点组成并且节点值从1到n互不相同的不同二叉树，可以按照任意顺序
 * 解析：求解1~n能组成多少个不同的二叉搜索树
 * @author liaojiamin
 * @Date:Created in 10:15 2021/7/22
 */
public class BuildGenerateTrees {

    public static void main(String[] args) {
        List<BinaryNode> binaryNodes = new ArrayList<>();
//        List<String> middleSearch  = new ArrayList<>();
//        binaryNodes = generateTrees(4);
//        for (int i = 0; i < binaryNodes.size(); i++) {
//            StringBuilder strBu = new StringBuilder();
//            middleSearch.add(printTree(binaryNodes.get(i),strBu));
//        }
//        middleSearch.forEach(System.out::println);

        binaryNodes = generateTreesBinary(1, 4);
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (BinaryNode binaryNode : binaryNodes) {
            binarySearchTree.printTree(binaryNode);
        }

    }


    /**
     * 方法二
     *
     * */
    public static List<BinaryNode> generateTreesBinary(int start, int end){
        List<BinaryNode> binaryNodes = new LinkedList<>();
        if(start > end){
            binaryNodes.add(null);
            return binaryNodes;
        }
        for (int i=start;i<=end;i++){
            List<BinaryNode> leftNode = generateTreesBinary(start, i-1);
            List<BinaryNode> rightNode = generateTreesBinary(i+1, end);
            for (BinaryNode left : leftNode) {
                for (BinaryNode right : rightNode) {
                    binaryNodes.add(new BinaryNode(i, left, right));
                }
            }
        }
        return binaryNodes;
    }


    /**
     * 方法一
     * 构造二叉树全排列：构造数组全排列，数组构造成二叉树，通过前序遍历值筛选二叉树
     * */
    public static List<BinaryNode> generateTrees(int n){
        List<BinaryNode> binaryNodes = new ArrayList<>();
        List<String> middleSearchStr = new ArrayList<>();
        int[] array = new int[n];
        for (int i = 1; i <= n; i++) {
            array[i-1] = i;
        }
        return buildGenerateTree(array, binaryNodes, 0, middleSearchStr);
    }

    /**
     * 生成数组的全排列，每种排列按顺序生成二叉树
     * */
    public static List<BinaryNode> buildGenerateTree(int[] array, List<BinaryNode> binaryNodes, int start, List<String> middleSearchStr){
        if(array == null || array.length <=1 || start == (array.length -1)){
            BinaryNode newNode = buildBinarySearchTree(array);
            StringBuilder str = new StringBuilder();
            String middleStr = printTree(newNode, str );
            if(!middleSearchStr.contains(middleStr)){
                middleSearchStr.add(middleStr);
                binaryNodes.add(newNode);
            }
        }
        for (int i = start; i < array.length; i++) {
            int temp = array[i];
            array[i] = array[start];
            array[start] = temp;
            buildGenerateTree(array, binaryNodes, start+1, middleSearchStr);
            temp = array[i];
            array[i] = array[start];
            array[start] = temp;
        }
        return binaryNodes;
    }

    /**
     * 前序遍历字符集合
     * */
    public static String printTree(BinaryNode t, StringBuilder strbu) {
        if (t == null || t.getElement() == null) {
            return strbu.toString();
        }
        for (int i = 0; i < t.getCount(); i++) {
            strbu.append(t.getElement() + ":" + t.getHeight()+" ");
        }
        printTree(t.getLeft(), strbu);
        printTree(t.getRight(), strbu);
        return strbu.toString();
    }

    /**
     * 根据数组构造二叉树
     * */
    public static BinaryNode buildBinarySearchTree(int[] array){
        if(array == null || array.length <= 0){
            return null;
        }
        BinaryNode node = new BinaryNode(null, null, null);
        for (int i = 0; i < array.length; i++) {
            node = insertNode(node, array[i]);
        }
        return node;
    }

    /**
     * 插入节点构造二叉搜索树
     * */
    public static BinaryNode insertNode(BinaryNode node, Integer k){
        if(k == null){
            return node;
        }
        if(node == null || node.getElement() == null){
            node = new BinaryNode(k, null, null);
        }
        int validateK = node.compareTo(k);
        if(validateK > 0){
            node.setLeft(insertNode(node.getLeft(), k));
        }else if (validateK < 0){
            node.setRight(insertNode(node.getRight(), k));
        }
        return node;
    }
}
