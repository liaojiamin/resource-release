package com.ljm.resource.math.myList;

/**
 * 约瑟夫环问题： 将0,1,2,3,4.....n 这n个数字排列成一个圈圈，从数字0 开始数m个数，删掉他，
 * 接着从m+1 个开始数在来m个继续删除，一次类推，求出剩下的最后一个数据
 * @author liaojiamin
 * @Date:Created in 14:30 2021/7/7
 */
public class JosephusForList {
    public static void main(String[] args) {
        System.out.println(delNodeForJosephus(40000,997));
//        System.out.println(fixJosephusForMath(40000, 997));
        System.out.println(fixJosephusForMath2(40000,997));
    }

    /**
     * 数学方案：通过计算得出发f(n,m)
     * n=0  f(n,m) = 1
     * n>1  f(n,m) = [f(n-1,m)+m]%n
     *
     * */
    public static Integer fixJosephusForMath(Integer n, Integer m){
        if(n <= 0|| m<=0){
            return null;
        }
        if(n == 1){
            return 0;
        }
        if(n > 1){
            return (fixJosephusForMath(n-1, m)+m)%n;
        }
        return null;
    }

    /**
     * 动态规划实现
     * 数学方案：通过计算得出发f(n,m)
     * n=0  f(n,m) = 1
     * n>1  f(n,m) = [f(n-1,m)+m]%n
     *
     * */
    public static Integer fixJosephusForMath2(Integer n, Integer m){
        if(n <= 0|| m<=0){
            return null;
        }
        int last = 0;
        for(int i=2;i<=n;i++){
            last = (last+m)%i;
        }
        return last;
    }

    /**
     * 环形链表方法
     * */
    public static Integer delNodeForJosephus(Integer n, Integer m){
        if(n <= 0|| m<=0){
            return null;
        }
        //构造环形队列
        ListNode head = new ListNode(0);
        ListNode last = null;
        ListNode position = head;
        for (Integer i = 1; i < n; i++) {
            last = new ListNode(i);
            position.setNext(last);
            position = position.getNext();
        }
        last.setNext(head);
        ListNode before = position;
        position = position.getNext();

        //当需要移动的位置小于当前数据个数，直接移动指针
        Integer count = n;
        while (m <= count){
            for (Integer i = 0; i < m-1; i++) {
                before = position;
                position = position.getNext();
            }
            before.setNext(position.getNext());
            position = position.getNext();
            count--;
        }
        //当m 大于当前需要移动位置个数，取余计算最小移动值
        while (m > count && count > 1){
            int move = Math.abs(m%count - 1);
            for (Integer i = 0; i< move;i++){
                before = position;
                position = position.getNext();
            }
            before.setNext(position.getNext());
            position = position.getNext();
            count--;
        }
        position.setNext(null);
        return position.getValue();
    }

}
