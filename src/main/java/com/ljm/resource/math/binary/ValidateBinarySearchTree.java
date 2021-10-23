package com.ljm.resource.math.binary;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 给定一棵树，判断当前二叉树是否二叉搜索树
 * @author liaojiamin
 * @Date:Created in 12:12 2021/7/24
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        BinaryNode node6 = new BinaryNode(6, null, null);
        BinaryNode node3 = new BinaryNode(3, null, null);
        BinaryNode node4 = new BinaryNode(4, node3, node6);
        BinaryNode node1 = new BinaryNode(1, null, null);
        BinaryNode node5 = new BinaryNode(5, node1, node4);
        System.out.println(isValidBST(node5));

        System.out.println(validateBST(node5));
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        System.out.println(validateBST(node));
        System.out.println(validateBSTNode(node));
    }

    /**
     * 递归判断子节点
     * */
    public static boolean validateBSTNode(BinaryNode binaryNode ){
        if(binaryNode == null){
            return false;
        }
        return validateBST(binaryNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateBST(BinaryNode binaryNode, int min, int max){
        if(binaryNode == null){
            return true;
        }
        if(binaryNode.compareTo(min) <= 0 || binaryNode.compareTo(max) >= 0 ){
            return false;
        }
        Integer ele = Integer.valueOf(binaryNode.getElement().toString());
        return validateBST(binaryNode.getRight(), ele, max)
                && validateBST(binaryNode.getLeft(), min, ele);
    }
    /**
     * 直接判断中序遍历是否从小到大递增
     * */
    public static boolean validateBST(BinaryNode binaryNode){
        LinkedList<Integer> linkedList = new LinkedList<>();
        return validateMiddle(binaryNode, linkedList);
    }


    /**
     * 直接判断中序遍历是否从小到大递增
     * */
    public static boolean validateMiddle(BinaryNode binaryNode, LinkedList<Integer> eleList){
        if(binaryNode == null){
            return true;
        }
        boolean left = validateMiddle(binaryNode.getLeft(), eleList);
        Integer ele = Integer.valueOf(binaryNode.getElement().toString());
        if(eleList.size() > 0 && eleList.removeFirst() >= ele){
            return false;
        }else{
            eleList.add(ele);
        }
        boolean right = validateMiddle(binaryNode.getRight(), eleList);
        return left && right;
    }

    public static boolean validateList(List<Integer> eleList){
        if(eleList == null){
            return false;
        }
        for (int i = 1; i < eleList.size(); i++) {
            System.out.println(eleList.get(i));
            if(eleList.get(i) <= eleList.get(i-1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 错误情况：递归判断，用于都需要判断上一节点的上一节点的大小
     * */
    public static boolean isValidBST(BinaryNode binaryNode){
        if(binaryNode == null){
            return false;
        }
        if(binaryNode.getLeft() != null){
            if(binaryNode.getLeft().compareTo(binaryNode) >= 0){
                return false;
            }
        }
        if(binaryNode.getRight() != null){
            if(binaryNode.getRight().compareTo(binaryNode) <= 0){
                return false;
            }
        }
        return validateLeftTree(binaryNode.getLeft(), binaryNode) && validateRightTree(binaryNode.getRight(), binaryNode);

    }

    /**
     * 判断二叉树是否二叉搜索树
     * */
    public static boolean validateLeftTree(BinaryNode binaryNode, BinaryNode myRoot){
        if(binaryNode == null){
            return true;
        }
        if(binaryNode.getLeft()!= null ){
            if( binaryNode.getLeft().compareTo(binaryNode) >= 0){
                return false;
            }
            if (binaryNode.getLeft().compareTo(myRoot) >= 0){
                return false;
            }
        }
        if(binaryNode.getRight() != null){
            if(binaryNode.getRight().compareTo(binaryNode) <= 0){
                return false;
            }
            if (binaryNode.getRight().compareTo(myRoot) >= 0){
                return false;
            }
        }
        return validateLeftTree(binaryNode.getLeft(), binaryNode) && validateRightTree(binaryNode.getRight(), binaryNode);
    }

    /**
     * 判断二叉树是否二叉搜索树
     * */
    public static boolean validateRightTree(BinaryNode binaryNode, BinaryNode myRoot){
        if(binaryNode == null){
            return true;
        }
        if(binaryNode.getLeft()!= null ){
            if( binaryNode.getLeft().compareTo(binaryNode) >= 0){
                return false;
            }
            if (binaryNode.getLeft().compareTo(myRoot) <= 0){
                return false;
            }
        }
        if(binaryNode.getRight() != null){
            if(binaryNode.getRight().compareTo(binaryNode) <= 0){
                return false;
            }
            if (binaryNode.getRight().compareTo(myRoot) <= 0){
                return false;
            }
        }
        return validateLeftTree(binaryNode.getLeft(), binaryNode) && validateRightTree(binaryNode.getRight(), binaryNode);
    }
}

