package com.ljm.resource.math.binary;

import com.ljm.resource.math.myList.ListNode;
import com.ljm.resource.math.myList.MyLinkedList;

/**
 * 二叉树节点对象定义
 *
 * @author liaojiamin
 * @Date:Created in 15:24 2020/12/11
 */
public class BinaryNode implements Comparable {
    private Object element;
    private BinaryNode left;
    private BinaryNode right;
    /**
     * 树高度
     */
    private int height;
    private int count;
    private ListNode linkedList;

    public BinaryNode(Object element, BinaryNode left, BinaryNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.count = 1;
        this.height = 0;
    }

    public ListNode getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(ListNode linkedList) {
        this.linkedList = linkedList;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        int flag;
        if (o instanceof Integer) {
            int myElement = ((int) this.element == (int) o)
                    ? 0: ((int) this.element > (int) o) ? 1 : -1;
            flag = myElement > 0 ? 1 : myElement == 0 ? 0 : -1;
        } else if (o instanceof  BinaryNode){
            int myElement = ((int) this.element == (int)((BinaryNode) o).getElement() )
                    ? 0 : ((int) this.element > (int)((BinaryNode) o).getElement()) ? 1 : -1;
            flag = myElement > 0 ? 1 : myElement == 0 ? 0 : -1;
        }else {
            flag = this.element.toString().compareTo(o.toString());
        }

        if (flag == 0) {
            return 0;
        } else if (flag > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
