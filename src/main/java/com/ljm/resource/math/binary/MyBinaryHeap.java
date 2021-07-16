package com.ljm.resource.math.binary;

import java.util.Random;

/**
 * 二叉堆
 * @author liaojiamin
 * @Date:Created in 11:35 2021/3/8
 */
public class MyBinaryHeap {
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 当前最后一个数据下标
     * */
    private int currentSize;
    private AnyType[] array;

    public MyBinaryHeap(){
        currentSize = 0;
        array = new AnyType[DEFAULT_CAPACITY];
    }

    public MyBinaryHeap(int capacity){
        currentSize = 0;
        array = new AnyType[capacity];
    }

    public MyBinaryHeap(AnyType[] items){
        currentSize = items.length -1;
        array = new AnyType[(items.length + 2) * 11/10];
        for (int i = 0; i < items.length; i++) {
            array[i] = items[i];
        }
        buileHeap();
    }

    public void buileHeap(){
        for(int i = currentSize/2;i>0;i--){
            percolateDown(i);
        }
    }

    /**
     * 清空队列
     * */
    public void makeEmpty(){
        currentSize = 1;
        array = new AnyType[DEFAULT_CAPACITY];
    }

    /**
     * 判断是否为空，数组第一个值空置预留
     * */
    public boolean isEmpty(){
        if(array == null || array.length <= 0 || currentSize == 0){
            return true;
        }
        for (AnyType anyType : array) {
            if(anyType != null){
                return false;
            }
        }
        return true;
    }

    /**
     * 查找最小值
     * */
    public AnyType findMin(){
        if(isEmpty()){
            return null;
        }
        return array[1];
    }


    /**
     * 删除最小值
     * */
    public AnyType deleteMin(){
        if(isEmpty()){
            return null;
        }
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    /**
     * 降层
     */
    public void percolateDown(int hole){
        int child;
        //摘除需要渐层的节点
        AnyType tmp = array[hole];
        //hole = child --> hole = hole*2, 当前值 --> 降层值
        for(;hole*2 <= currentSize;hole = child){
            //确定子节点位置，2i 和 2i+1
            child = 2*hole;
            //判断子节点1 与子节点2 大小，找出最小的与hole位置交换
            if(child <= currentSize && array[child].compareTo(array[child +1]) > 0){
                child++;
            }
            //交换
            if(array[child].compareTo(tmp) > 0){
                array[hole] = array[child];
            }else {
                break;
            }
        }
        //最终位置
        array[hole] = tmp;
    }

    /**
     * 添加节点
     */
    public void insert(AnyType x){
        if(currentSize == array.length){
            enlargeArray(array.length * 2 +1);
        }
        int hole = ++currentSize;
        for(array[0] = x; x.compareTo(array[hole/2]) < 0; hole/=2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    /**
     * 扩容
     * */
    public void enlargeArray(int newSize){
        if(newSize < array.length){
            return;
        }
        AnyType[] newArrays = new AnyType[newSize];
        for (int i = 0; i < array.length; i++) {
            newArrays[i] = array[i];
        }
        array = newArrays;
    }

    /**
     * 打印
     * */
    public void print(){
        if(isEmpty()){
            System.out.println("array is empty");
        }
        for (int i = 1; i < array.length; i++) {
            System.out.println(array[i].getElement());
        }
    }

    public static void main(String[] args) {
        Integer size = 10;
        MyBinaryHeap heap = new MyBinaryHeap((size + 2 )*11/10);
        Random random = new Random(100);
        for (int i = 0; i < 30; i++) {
            heap.insert(new AnyType(random.nextInt(100)));
        }
        heap.print();
        System.out.println(heap.findMin().getElement());

    }
}
