package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * Avl树（二叉搜索树）定义以及实现
 * @author liaojiamin
 * @Date:Created in 15:31 2020/12/16
 */
public class AvlTree {
    public void makeEmpty(BinaryNode root) {
        root = null;
    }

    public boolean isEmpty(BinaryNode root) {
        return root == null;
    }

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        AvlTree searchTree = new AvlTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            node = searchTree.insert(random.nextInt(100), node);
        }
        System.out.println(searchTree.findMax(node).getElement());
        System.out.println(searchTree.findMin(node).getElement());
        searchTree.printTree(node);
        if(!searchTree.contains(13, node)){
            node = searchTree.insert(13, node);
        }
        System.out.println(searchTree.contains(13, node));
        node = searchTree.remove(13, node);
        System.out.println(searchTree.contains(13, node));
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

    private static final int MAXBALANCE_HEIGH = 1;
    /**
     * 平衡查找二叉树
     *
     * */
    public BinaryNode balance(BinaryNode t){
        if(t == null){
            return t;
        }
        if(height(t.getLeft()) - height(t.getRight()) > MAXBALANCE_HEIGH){
            if(height(t.getLeft().getLeft()) >= height(t.getLeft().getRight())){
                t = rotateWithLeftChild(t);
            }else {
                t = doubleWithLeftChild(t);
            }
        }else if(height(t.getRight()) - height(t.getLeft()) > MAXBALANCE_HEIGH ){
            if(height(t.getRight().getRight()) >= height(t.getRight().getLeft())){
                t = rotateWithRightChild(t);
            }else {
                t = doubleWithRightchild(t);
            }
        }
        t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
        return t;
    }

    /**
     * 左单旋一次
     * */
    private BinaryNode rotateWithLeftChild(BinaryNode k2){
        BinaryNode k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), height(k2)) + 1);
        return k1;
    }

    /**
     * 右单旋一次
     * */
    private BinaryNode rotateWithRightChild(BinaryNode k2){
        BinaryNode k1 = k2.getRight();
        k2.setRight(k1.getLeft());
        k1.setLeft(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k2), height(k1.getRight())) + 1);
        return k1;
    }

    /**
     * 右旋转 接左旋转，双旋转
     * */
    private BinaryNode doubleWithLeftChild(BinaryNode k3){
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }

    /**
     * 左旋转 接右旋转，双旋转
     * */
    private BinaryNode doubleWithRightchild(BinaryNode k3){
        k3.setRight(rotateWithLeftChild(k3.getRight()));
        return rotateWithRightChild(k3);
    }

    /**
     * 获取树高度
     * */
    private int height(BinaryNode t){
        return t == null ? -1 : t.getHeight();
    }

    /**
     * 插入节点
     *
     * @author: liaojiamin
     * @date: 15:48 2020/12/15
     */
    private BinaryNode insert(Object x, BinaryNode t) {
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
        return balance(t);
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
        return balance(t);
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
            System.out.print(t.getElement() + " ");
        }
        printTree(t.getRight());
    }
}
