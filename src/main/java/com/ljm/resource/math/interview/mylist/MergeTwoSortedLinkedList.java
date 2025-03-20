package com.ljm.resource.math.interview.mylist;

/**
 * Created by jiamin5 on 2023/3/11.
 */
public class MergeTwoSortedLinkedList {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     * 递归
     * */
    public static ListNode mergeTwoLists_2(ListNode head1, ListNode head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        if(head1.val < head2.val){
            head1.next  = mergeTwoLists_2(head1.next, head2);
            return head1;
        }
        head2.next = mergeTwoLists_2(head1,head2.next);
        return head2;
    }

    /**
     * 合并两个链表,指针法
     * */
    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
       if(head1 == null){
           return head2;
       }
       if(head2 == null){
           return head1;
       }
       ListNode head = head1.val > head2.val ? head2 : head1;
       ListNode current = head;
       ListNode next = head.next;
       ListNode pre = head1.val > head2.val ? head1 : head2;
       while (next != null && pre != null){
           if(pre.val < next.val){
               current.next = pre;
               pre = pre.next;
           }else {
               current.next = next;
               next = next.next;
           }

           current = current.next;
       }
       current.next = (pre !=  null? pre : next);
       return head;
    }
}
