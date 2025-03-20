package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * Created by jiamin5 on 2023/3/8.
 */
public class CountNodes {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(500000);
        BinaryNode node = new BinaryNode(1, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        CountNodes countNodes = new CountNodes();
        System.out.println(countNodes.countNodes(node));

        System.out.println(countNodes.countNodes(node));
        System.out.println(countNodes.countNodes_2(node));
//        System.out.println(countNodes.countNodes_3(node));

    }



    /**
     * 二进制 + 二分查找
     * */
    public int countNodes_3(BinaryNode root){
        if(root == null){
            return 0;
        }
        int height = 0;
        BinaryNode tempNode = root;
        while (tempNode.getLeft() != null){
            height++;
            tempNode = tempNode.getLeft();

        }
        int min = 1 << height;
        int max = (1 << (height+1)) -1;
        while (min < max){
            //中间节点
            int temp = min + (max - min+1)/2;
            if(exists(root, temp, height)){
                min = temp;
            }else {
                max = temp-1;
            }
        }
        return min;
    }

    public boolean exists(BinaryNode root, int temp, int heignt){
        int position = 1 << (heignt -1);
        BinaryNode node = root;
        while (node != null && position > 0){
            if((temp & position) != 0){
                node = node.getRight();
            }else {
                node = node.getLeft();
            }
            position >>= 1;
        }

        return node != null;
    }



    public int countNodes_2(BinaryNode root){
        if(root == null){
            return 0;
        }
        int left = countNodes_2(root.getLeft());
        int right = countNodes_2(root.getRight());
        return left + right + 1;
    }


    public int countNodes(BinaryNode root) {
        countNode = 0;
        if(root == null){
            return 0;
        }
        Integer countNode = countNode(root);
        System.out.println();
        return countNode;
    }
    private static Integer countNode = 0;

    public Integer countNode(BinaryNode root){
        System.out.print(root.getElement() + ", ");
        countNode++;
        if(root.getLeft() != null){
            countNode(root.getLeft());
        }
        if(root.getRight() != null){
            countNode(root.getRight());
        }
        return countNode;

    }
}
