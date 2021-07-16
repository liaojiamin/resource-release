package com.ljm.resource.math.myList;

import com.ljm.resource.math.myStack.MyStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 单向链表实现
 *
 * @author liaojiamin
 * @Date:Created in 12:21 2021/3/5
 */
public class MyLinkedList {

    /**
     * 链表尾部添加节点
     */
    public static ListNode addToTail(ListNode head, String key, Integer value) {
        ListNode newNode = new ListNode(key, value);
        if (head == null) {
            head = newNode;
        }else {
            ListNode pointNode = head;
            while (pointNode.getNext() != null){
                pointNode = pointNode.getNext();
            }
            pointNode.setNext(newNode);
        }
        return head;
    }

    /**
     * 链表尾部添加节点
     */
    public static ListNode addToTail(ListNode head, ListNode newNode) {
        if (head == null) {
            head = newNode;
        }else {
            ListNode pointNode = head;
            while (pointNode.getNext() != null){
                pointNode = pointNode.getNext();
            }
            pointNode.setNext(newNode);
        }
        return head;
    }

    /**
     * 从头打印链表数据
     * */
    public static void print(ListNode head){
        if(head == null){
            System.out.println("is empty list");
        }
        ListNode pointNode = head;
        while (pointNode != null){
            if(pointNode.getKey() != null){
                System.out.print(pointNode.getKey());
            }else {
                System.out.print(pointNode.getValue());
            }
            System.out.print(", ");
            pointNode = pointNode.getNext();
        }
    }

    /**
     * 查找节点
     * */
    public static ListNode search(ListNode head, Integer value){
        if(head == null || value == null){
            System.out.println("not in");
        }
        ListNode pointNode = head;
        while (pointNode != null){
            if(pointNode.getValue() == value){
                System.out.println("is in");
                return pointNode;
            }else {
                pointNode = pointNode.getNext();
            }
        }
        System.out.println("not in");
        return null;
    }

    /**
     * 通过key查找节点
     * */
    public static ListNode search(ListNode head, String key){
        if(head == null || key == null){
            System.out.println("not in");
        }
        ListNode pointNode = head;
        while (pointNode != null){
            if(pointNode.getKey().equals(key)){
                System.out.println("is in");
                return pointNode;
            }else {
                pointNode = pointNode.getNext();
            }
        }
        System.out.println("not in");
        return null;
    }

    /**
     * 删除节点
     * */
    public static ListNode delete(ListNode head, Integer value){
        if(head == null || value == null || head.getNext() == null){
            return head;
        }
        ListNode delNode= null;
        if(head.getValue() == value){
            delNode = head.getNext();
        }else {
            ListNode pointNode = head;
            while (pointNode.getNext() != null && pointNode.getNext().getValue() != value){
                pointNode = pointNode.getNext();
            }
            if(pointNode.getNext() != null && pointNode.getNext().getValue() == value){
                delNode = pointNode.getNext();
                pointNode.setNext(pointNode.getNext().getNext());
            }
        }
        if(delNode != null){
            System.out.println("delete success val="+ delNode.getValue());
        }
        return head;
    }

    /**
     * 栈方法
     * 从尾到头打印单向链表值
     * */
    public static void printOver(ListNode head){
        MyStack myStack = new MyStack();
        if(head == null){
            return;
        }
        ListNode pointNode = head;
        while (pointNode != null){
            myStack.push(pointNode.getValue().toString());
            pointNode = pointNode.getNext();
        }
        while (!myStack.isEmpty()){
            System.out.print(myStack.pop());
            System.out.print(", ");
        }
    }

    /**
     *
     * 查找单向链表倒数第k个节点
     */
    public static ListNode findLastKNode(ListNode head, int k){
        if(head == null || k == 0){
            return new ListNode(-1);
        }
        ListNode beforeNode = head;
        ListNode afterNode = head;
        for (int i = 0; i < k - 1; i++) {
            if(beforeNode.getNext() != null){
                beforeNode = beforeNode.getNext();
            }else {
                return new ListNode(-1);
            }
        }
        //确保有 k 个节点
        if(beforeNode.getNext() == null){
            return new ListNode(-1);
        }
        while (beforeNode.getNext() != null){
            beforeNode = beforeNode.getNext();
            afterNode = afterNode.getNext();
        }
        return afterNode;
    }

    /**
     * 反转单向链表
     * */
    public static ListNode reverOverListNode(ListNode head){
        if(head == null){
            return head;
        }
        ListNode before = head;
        ListNode middle = head;
        ListNode after = head;
        if(middle.getNext() == null){
            return head;
        }
        middle = middle.getNext();
        if(middle.getNext() == null){
            middle.setNext(before);
            head.setNext(null);
            return middle;
        }
        after = middle.getNext();
        while (after.getNext() != null){
            middle.setNext(before);
            before = middle;
            middle = after;
            after = after.getNext();
        }
        //处理最后两个节点的指针
        head.setNext(null);
        middle.setNext(before);
        after.setNext(middle);
        return after;
    }

    /**
     * 递归合并两个顺序链表
     * */
    public static ListNode mixTwoSortList(ListNode sortOne, ListNode sortTwo){
        if(sortOne == null && sortTwo == null){
            return new ListNode();
        }
        if(sortOne == null){
            return sortTwo;
        }
        if(sortTwo == null){
            return sortOne;
        }
        ListNode mergeHead = null;
        if(sortOne.getValue() < sortTwo.getValue()){
            mergeHead = sortOne;
            mergeHead.setNext(mixTwoSortList(sortOne.getNext(), sortTwo));
        }else {
            mergeHead = sortTwo;
            mergeHead.setNext(mixTwoSortList(sortTwo.getNext(), sortOne));
        }
        return mergeHead;
    }

    /**
     * 递归方式
     * 从尾到头打印单向链表
     * */
    public static void printOver_2(ListNode head){
        if(head == null){
            return;
        }
        ListNode pointNode = head;
        if(pointNode.getNext() != null){
            printOver_2(pointNode.getNext());
        }
        System.out.print(pointNode.getValue());
        System.out.print(", ");
    }

    /**
     * 构建复杂链表，链表next节点指向下一个节点，
     * before节点指向链表中任意节点
     * */
    public static ListNode buildComplexListNode(){
        Random random = new Random();
        ListNode pHead = new ListNode(random.nextInt(100));
        ListNode[] nodeArray = new ListNode[20];
        for (int i = 0; i < 20; i++) {
            ListNode newNode = new ListNode(random.nextInt(100));
            nodeArray[i] = newNode;
            ListNode headNode = pHead;
            while (headNode.getNext() !=  null){
                headNode = headNode.getNext();
            }
            headNode.setNext(newNode);
        }
        print(pHead);
        ListNode  headNode = pHead;
        while (headNode.getNext() != null){
            headNode.setBefore(nodeArray[random.nextInt(nodeArray.length -1)]);
            headNode = headNode.getNext();
        }
        return pHead;
    }

    /**
     * 复制复杂链表
     * 方法一：空间换时间，
     * 先遍历一次oldList，复制next指针，并且将oldNode，newNode存储到map中，
     * 第二次遍历同时遍历oldList，newList，直接通过oldList的before指向的oldListNode从mep中获取newNode，
     * O(1)时间获取到before值
     * */
    public static ListNode complexListNode(ListNode pHead){
        if(pHead == null || pHead.getNext() == null){
            return pHead;
        }
        Map<ListNode, ListNode> oldToNewNode = new HashMap<>();
        ListNode complexNode = pHead;
        ListNode newHead = null;
        ListNode myHeadNode = null;
        while (complexNode != null){
            ListNode createNode = new ListNode(complexNode.getValue());
            oldToNewNode.put(complexNode, createNode);
            if(newHead == null){
                newHead = createNode;
                myHeadNode = newHead;
            }else {
                myHeadNode.setNext(createNode);
                myHeadNode = myHeadNode.getNext();
            }
            complexNode = complexNode.getNext();
        }
        ListNode beforeComplexNode = pHead;
        ListNode beforeSetNode = newHead;
        while (beforeComplexNode.getNext() != null && beforeSetNode.getNext() != null){
            if(oldToNewNode.containsKey(beforeComplexNode.getBefore())){
                beforeSetNode.setBefore(oldToNewNode.get(beforeComplexNode.getBefore()));
            }
            beforeComplexNode = beforeComplexNode.getNext();
            beforeSetNode = beforeSetNode.getNext();
        }
        return newHead;
    }

    /**
     * 复制复杂链表
     * 方法二：
     * 第一次遍历，直接将范问到的节点复制新节点，并且插入到范问到的节点后面
     * 第二次遍历，设置新建节点的before节点，因为每个新节点A‘都在老节点A的next位置，所以，老节点的before B节点的next 就是改节点的新节点B’
     * 那么新节点A‘的before节点就是B’
     * 第三次遍历，将奇数项的节点单独拆分成一个链表得到我们复制的链表，偶数位项就是老的链表
     */
    public static ListNode complexListNode_2(ListNode pHead){
        if(pHead == null || pHead.getNext() == null){
            return pHead;
        }
        ListNode complexNode = pHead;
        while (complexNode.getNext() != null){
            ListNode createNode = new ListNode(complexNode.getValue());
            createNode.setNext(complexNode.getNext());
            complexNode.setNext(createNode);
            complexNode = complexNode.getNext().getNext();
        }
        ListNode beforeComplexNode = pHead;
        while (beforeComplexNode.getNext() != null){
            if(beforeComplexNode.getBefore() != null){
                beforeComplexNode.getNext().setBefore(beforeComplexNode.getBefore().getNext());
            }
            beforeComplexNode = beforeComplexNode.getNext().getNext();
        }
        ListNode fixComplexNode = pHead;
        ListNode resultHead = null;
        ListNode resultNode = null;
        while (fixComplexNode.getNext() != null){
            if(resultHead == null){
                resultHead = fixComplexNode.getNext();
                resultNode = resultHead;
            }else {
                resultNode.setNext(fixComplexNode.getNext());
                resultNode = resultNode.getNext();
            }
            fixComplexNode = fixComplexNode.getNext().getNext();
        }
        return resultHead;
    }

    public static void main(String[] args) {
//        Random random = new Random(100);
//        ListNode myHead = new ListNode(random.nextInt(100));
//        for (int i = 0; i < 22; i++) {
//            addToTail(myHead, random.nextInt(100));
//        }
//        print(myHead);
//        System.out.println();
//        System.out.println(findLastKNode(myHead, 99).getValue() );
//        System.out.println();
//        ListNode reverOverNodeResult = reverOverListNode(myHead);
//        print(reverOverNodeResult);
//        if(search(myHead, 74)){
//            delete(myHead, 74);
//        }
//        print(myHead);
//        System.out.println();
//        printOver(myHead);
//        System.out.println();
//        printOver_2(myHead);
//        Integer base =  12;
//        ListNode myHead1 = new ListNode(base);
//        Random random = new Random(100);
//        for (int i = 0; i < 22; i++) {
//            base = base + random.nextInt(100);
//            addToTail(myHead1, base);
//        }
//        print(myHead1);
//        System.out.println("----------------------------");
//        Integer base2 =  12;
//        ListNode myHead2 = new ListNode(base2);
//        for (int i = 0; i < 22; i++) {
//            base = base + random.nextInt(100);
//            addToTail(myHead2, base);
//        }
//        print(myHead2);
//        System.out.println("----------------------------");
//        print(mixTwoSortList(myHead1, myHead2));
        ListNode oldListNode = buildComplexListNode();
        System.out.println("oldListNode:");
        printListNode(oldListNode);
        System.out.println("newListNode:");
        ListNode mewListNode = complexListNode(oldListNode);
        printListNode(mewListNode);

        ListNode newListNode_2 = complexListNode_2(oldListNode);
        System.out.println("newListNode_2:");
        printListNode(newListNode_2);
    }

    public static void printListNode(ListNode pHead){
        ListNode printNode = pHead;
        while (printNode.getNext() != null){
            System.out.print("value: " + printNode.getValue());
            System.out.print(", ");
            System.out.print("before: " + (printNode.getBefore() != null ? printNode.getBefore().getValue() : ""));
            System.out.print(", ");
            System.out.print("next: " + (printNode.getNext() != null ? printNode.getNext().getValue() : ""));
            printNode = printNode.getNext();
            System.out.println();
        }
    }
}
