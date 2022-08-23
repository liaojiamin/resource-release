package com.ljm.resource.math.binary;

import java.util.Arrays;
import java.util.Random;

/**
 * 有序数组转换为高度平衡二叉搜索树（AVL树）
 * @author liaojiamin
 * @Date:Created in 16:34 2022/2/21
 */
public class SortArrayToAvlTree {
    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            if(i == 0){
                arr[i] = random.nextInt(30);
            }else {
                arr[i] = random.nextInt(30) + arr[i-1];
            }
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        BinaryNode avlTree = sortArrayToAVL(arr);
        printTreeMiddle(avlTree);
    }

    /**
     * 中序遍历
     * */
    public static void printTreeMiddle(BinaryNode binaryNode){
        if(binaryNode == null){
            return;
        }
        printTreeMiddle(binaryNode.getLeft());
        System.out.println(binaryNode.getElement());
        printTreeMiddle(binaryNode.getRight());
    }


    /**
     * 有序数组递归构建AVL树
     * */
    public static BinaryNode sortArrayToAVL(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        if(arr.length == 1){
            return new BinaryNode(arr[0], null, null);
        }
        BinaryNode leftNode = sortArrayToAVL(Arrays.copyOfRange(arr, 0, (arr.length/2)));
        BinaryNode rightNode = sortArrayToAVL(Arrays.copyOfRange(arr, (arr.length/2)+1,arr.length));
        BinaryNode rootNode = new BinaryNode(arr[arr.length/2], leftNode, rightNode);
        return rootNode;
    }
}
