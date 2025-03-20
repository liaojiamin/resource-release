package com.ljm.resource.math.interview.mylist;

/**
 * Created by jiamin5 on 2023/3/27.
 * 用数组实现队列
 */
public class RingArray {

    public static class MyQueue{
        private int[]  arr;
        private int begin;
        private int end;
        private int size;
        private int limit;

        public MyQueue(int limit){
            arr = new int[limit];
            this.limit = limit;
            begin = 0;
            end = 0;
            size = 0;
        }

        public int netPosition(int thisEnd){
            if(thisEnd < limit - 1){
                return thisEnd+1;
            }
            return 0;
        }

        public void push(int value) throws Exception {
            if(size == limit){
                throw new Exception("队列满了");
            }
            size ++;
            arr[end] = value;
            end = netPosition(end);
        }

        public int poll() throws Exception {
            if(size == 0){
                throw new Exception("队列空了");
            }
            size --;
            int element =  arr[begin];
            begin = netPosition(begin);
            return element;
        }

        public boolean isEmpty(){
            return size == 0;
        }
    }

}
