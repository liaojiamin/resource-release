package com.ljm.resource.math.interview.mylist;

import java.util.Random;

/**
 * Created by jiamin5 on 2023/3/11.
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        ReverseNodesInKGroup.ListNode listNode = reverseNodesInKGroup.new ListNode(1);
        Random random = new Random();
        int n = 2;
        ReverseNodesInKGroup.ListNode head = listNode;
        while (head != null && n > 0){
            ReverseNodesInKGroup.ListNode listNodeNew = reverseNodesInKGroup.new ListNode(random.nextInt(100));
            head.next = listNodeNew;
            head = head.next;
            n--;
        }
        printListNode(listNode);
        reverseKGroup(listNode, 2);
        printListNode(listNode);

    }

    public static void  printListNode(ListNode listNode){
        ListNode head = listNode;
        while (head != null){
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(head, k);
        if(end == null){
            return head;
        }
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if(end == null){
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }



    public static ListNode getKGroupEnd(ListNode start, int k) {
        int count = 0;
        while (start != null && count < k-1) {
            count++;
            start = start.next;
        }
        return start;
    }



    public static void reverse(ListNode start, ListNode end) {
        if(end == null || start == null){
            return;
        }
        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        ListNode current = start;
        while (current != end){
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

        start.next = end;
    }
}
