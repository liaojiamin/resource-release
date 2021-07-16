package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 二叉查找树实现
 *
 * @author liaojiamin
 * @Date:Created in 15:42 2020/12/15
 */
public class BinarySearchTree {
    public void makeEmpty(BinaryNode root) {
        root = null;
    }

    public boolean isEmpty(BinaryNode root) {
        return root == null;
    }

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        System.out.println(searchTree.findMax(node).getElement());
        System.out.println(searchTree.findMin(node).getElement());
        searchTree.printTree(node);
//        if(!searchTree.contains(13, node)){
//            node = searchTree.insert(13, node);
//        }
//        System.out.println(searchTree.contains(13, node));
//        node = searchTree.remove(13, node);
//        System.out.println(searchTree.contains(13, node));
    }

    /**
     * 节点元素是否存在
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    private boolean contains(Object x, BinaryNode t) {
        if (x == null) {
            return false;
        }
        if (t == null) {
            return false;
        }
        int flag = t.compareTo(x);
        if (flag > 0) {
            return contains(x, t.getLeft());
        } else if (flag < 0) {
            return contains(x, t.getRight());
        } else {
            return true;
        }
    }

    /**
     * 查找最小元素节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    private BinaryNode findMin(BinaryNode t) {
        if (t == null) {
            return null;
        }
        if (t.getLeft() != null) {
            return findMin(t.getLeft());
        }
        return t;
    }

    /**
     * 查找最大元素节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    private BinaryNode findMax(BinaryNode t) {
        if (t == null) {
            return null;
        }
        if (t.getRight() != null) {
            return findMax(t.getRight());
        }
        return t;
    }

    /**
     * 插入节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    public BinaryNode insert(Object x, BinaryNode t) {
        if (x == null) {
            return t;
        }
        if (t == null || t.getElement() == null) {
            t = new BinaryNode(x, null, null);
            return t;
        }
        int flag = t.compareTo(x);
        if (flag > 0) {
            t.setLeft(insert(x, t.getLeft()));
        } else if (flag < 0) {
            t.setRight(insert(x, t.getRight()));
        } else {
            t.setCount(t.getCount() + 1);
        }
        return t;
    }

    /**
     * 插入节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    public BinaryNode insert(BinaryNode x, BinaryNode t) {
        if (x == null) {
            return t;
        }
        if (t == null || t.getElement() == null) {
            t = x;
            return t;
        }
        int flag = t.compareTo(x);
        if (flag > 0) {
            t.setLeft(insert(x, t.getLeft()));
        } else if (flag < 0) {
            t.setRight(insert(x, t.getRight()));
        } else {
            t.setCount(t.getCount() + 1);
        }
        return t;
    }

    /**
     * 删除节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    private BinaryNode remove(Object x, BinaryNode t) {
        if (x == null) {
            return t;
        }
        int flag = t.compareTo(x);
        if (flag > 0) {
            return remove(x, t.getLeft());
        } else if (flag < 0) {
            return remove(x, t.getRight());
        } else if (t.getLeft() != null && t.getRight() != null) {
            //找到对应节点，将节点右子树下面最小的值替换当前值
            BinaryNode min = findMin(t.getRight());
            t.setElement(min.getElement());
            //递归删除右子树下最小值
            remove(min.getElement(), t.getRight());
        } else {
            //找到对应节点，但是当前节点只有一个子节点
            // 递归思想：只考虑最简单情况，当只有当前节点与其左子节点，删除当前节点返回当节点左子节点，右节点同理
            t = t.getLeft() != null ? t.getLeft() : t.getRight();
        }
        return t;
    }

    /**
     * 按顺序打印节点信息：左中右
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    public void printTree(BinaryNode t) {
        if (t == null || t.getElement() == null) {
            return;
        }
        printTree(t.getLeft());
        for (int i = 0; i < t.getCount(); i++) {
            System.out.println(t.getElement() + ":" + t.getHeight());
        }
        printTree(t.getRight());
    }
}

