package com.ljm.resource.math.binary;

import com.ljm.resource.math.array.BinarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 9:59 2021/7/22
 */
public class GetMiddleSearch {

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        List<Integer> middleNode = inorderTraversal(node);
        for (Integer integer : middleNode) {
            System.out.print(integer+ " ");
        }
    }

    /**
     * 返回二叉树中序遍历节点信息
     * */
    public static List<Integer> inorderTraversal(BinaryNode binaryNode){
        List<Integer> middleNode = new ArrayList<>();
        return middleSearch(binaryNode, middleNode);
    }

    public static List<Integer> middleSearch(BinaryNode binaryNode, List<Integer> middleNode){
        if(binaryNode == null){
            return middleNode;
        }
        middleSearch(binaryNode.getLeft(), middleNode);
        middleNode.add(Integer.valueOf(binaryNode.getElement().toString()));
        middleSearch(binaryNode.getRight(), middleNode);
        return middleNode;
    }
}
