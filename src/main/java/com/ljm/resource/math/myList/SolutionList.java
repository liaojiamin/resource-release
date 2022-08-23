package com.ljm.resource.math.myList;

/**
 * 判断链表是否有环
 *
 * @author liaojiamin
 * @Date:Created in 11:17 2022/2/18
 */
public class SolutionList {
    public static void main(String[] args) {
        ListNode firstNode = new ListNode();
        for (int i = 0; i < 20; i++) {
            MyLinkedList.addToTail(firstNode, i + "_", i);
        }
        ListNode addNode = new ListNode(100+"", 100);
        ListNode head = firstNode;
        while (head != null){
            ListNode lastNode = head;
            head = head.getNext();
            if(head == null){
                lastNode.setNext(addNode);
                addNode.setNext(firstNode);
            }
        }
        System.out.println(hasCycle(firstNode));
    }

    public static boolean hasCycle(ListNode listNode) {
        if (listNode == null || listNode.getNext() == null) {
            return false;
        }
        ListNode head1 = listNode;
        ListNode head2 = listNode.getNext();
        while (head2 != null && head2.getNext() != null) {
            head2 = head2.getNext().getNext();
            head1 = head1.getNext();
            if (head2 == head1) {
                return true;
            }
        }
        return false;
    }
}
