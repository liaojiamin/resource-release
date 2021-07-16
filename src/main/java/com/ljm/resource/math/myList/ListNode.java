package com.ljm.resource.math.myList;

import com.ljm.resource.math.binary.BinaryNode;

/**
 * 链表元素节点
 *
 * @author liaojiamin
 * @Date:Created in 12:17 2021/3/5
 */
public class ListNode implements Comparable<ListNode> {
    private String key;
    private Integer value;
    private BinaryNode binaryNode;
    private ListNode next;
    private ListNode before;

    public ListNode() {
    }

    public ListNode(String key, Integer value){
        this.key = key;
        this.value = value;
        this.next = null;
        this.before = null;
    }
    public ListNode(Integer value) {
        this.value = value;
        this.next = null;
        this.before = null;
    }

    public ListNode(BinaryNode binaryNode) {
        this.binaryNode = binaryNode;
        this.next = null;
        this.before = null;
    }


    public BinaryNode getBinaryNode() {
        return binaryNode;
    }

    public void setBinaryNode(BinaryNode binaryNode) {
        this.binaryNode = binaryNode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getBefore() {
        return before;
    }

    public void setBefore(ListNode before) {
        this.before = before;
    }

    @Override
    public int compareTo(ListNode o) {
        return this.value - o.value;
    }
}

