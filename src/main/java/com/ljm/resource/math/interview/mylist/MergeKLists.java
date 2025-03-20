package com.ljm.resource.math.interview.mylist;

import java.util.PriorityQueue;

/**
 * Created by jiamin5 on 2023/3/14.
 * 给你一个链表数组，每个链表都已经按升序排列。

 请你将所有链表合并到一个升序链表中，返回合并后的链表。

  

 示例 1：

 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 输出：[1,1,2,3,4,4,5,6]
 解释：链表数组如下：
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 将它们合并到一个有序链表中得到。
 1->1->2->3->4->4->5->6

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/merge-k-sorted-lists
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeKLists {
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

    /**
     * 小根堆实现
     * */
    public ListNode mergeKLists_2(ListNode[] lists){
         if(lists == null || lists.length <= 0){
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((listNode1, listNode2) -> listNode1.val - listNode2.val);
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null){
                queue.add(lists[i]);
            }
        }
        ListNode head = null;
        ListNode position = null;
        while (queue.size() > 0){
            ListNode thisNode = queue.poll();
            if(head == null){
                head = thisNode;
                position = head;
            }else {
                position.next = thisNode;
                position = position.next;
            }
            if(thisNode.next != null){
                queue.add(thisNode.next);
            }
        }
        return head;
    }

    /**
     * 递归实现
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length <= 0){
            return null;
        }

        ListNode head = null;
        int position = 0;
        while (position < lists.length){
            head = mergeTwoLists(head, lists[position]);
            position++;
        }
        return head;
    }

    public ListNode mergeTwoLists(ListNode node1, ListNode node2){
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        if(node1.val < node2.val){
            node1.next = mergeTwoLists(node1.next, node2);
            return node1;
        }
        node2.next = mergeTwoLists(node1, node2.next);
        return node2;
    }


    public ListNode buildListNode(int[] nums){
        ListNode listNode = null;
        ListNode position = null;
        for (int num : nums) {
            if(listNode == null){
                listNode = new ListNode(num);
                position = listNode;
            }else {
                position.next = new ListNode(num);
                position = position.next;
            }
        }
        return listNode;
    }

    public void printListNode(ListNode listNode){
        ListNode position = listNode;
        while (position != null){
            System.out.print(position.val);
            position = position.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();
        ListNode[] listNodes = new ListNode[3];
        int[] nums1 = {1,4,5};
        int[] nums2 = {1,3,4};
        int[] nums3 = {2,6};
        listNodes[0] = mergeKLists.buildListNode(nums1);
        mergeKLists.printListNode(listNodes[0]);
        listNodes[1] = mergeKLists.buildListNode(nums2);
        mergeKLists.printListNode(listNodes[1]);
        listNodes[2] = mergeKLists.buildListNode(nums3);
        mergeKLists.printListNode(listNodes[2]);
//        mergeKLists.printListNode(mergeKLists.mergeKLists(listNodes));
        mergeKLists.printListNode(mergeKLists.mergeKLists_2(listNodes));
    }
}

