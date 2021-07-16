package com.ljm.resource.math.myList;

/**
 * 有序单向链表中删除相同的数字
 * @author liaojiamin
 * @Date:Created in 10:01 2021/7/6
 */
public class FindCommonNumberListInSortList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode("1",1);
        for (int i=2;i<20;i++){
            MyLinkedList.addToTail(listNode, i+"",i);
            if(i%2==0){
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
                MyLinkedList.addToTail(listNode, i+"",i);
            }
        }
        MyLinkedList.print(listNode);
        findCommonNode(listNode);
        System.out.println();
        MyLinkedList.print(listNode);
    }

    /**
     * 双指针去除顺序链表中相同值的节点
     * 时间复杂度O(n)，空间复杂度O(1)
     * */
    public static ListNode findCommonNode(ListNode listNode){
        if(listNode == null || listNode.getNext() == null){
            return listNode;
        }
        ListNode headFirst = listNode;
        ListNode headSecond = listNode.getNext();
        while (headSecond != null){
            if(headSecond.compareTo(headFirst) == 0){
                headFirst.setNext(headSecond.getNext());
            }else {
                headFirst = headSecond;
            }
            headSecond = headSecond.getNext();
        }
        return listNode;
    }
}
