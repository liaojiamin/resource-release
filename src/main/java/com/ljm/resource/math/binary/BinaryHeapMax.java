package com.ljm.resource.math.binary;

import java.util.Arrays;
import java.util.Random;

/**
 * 二叉堆，父节点总大于子节点（最大堆），用数组实现
 * @author liaojiamin
 * @Date:Created in 15:45 2020/12/24
 */
public class BinaryHeapMax {
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 当前最后一个数据下标
     * */
    private int currentSize;
    private AnyType[] array;

    public BinaryHeapMax(){}

    public BinaryHeapMax(int capacity){
        currentSize = 0;
        array = new AnyType[capacity];
    }

    public BinaryHeapMax(AnyType[] item){
        currentSize = item.length;
        array = new AnyType[(currentSize + 2 )*11/10];
        int i =1;
        for (AnyType anyType : item) {
            array[i++] = anyType;
        }
        buildHeap();
    }


    public AnyType[] getAppHeapData(){
        return Arrays.copyOfRange(array, 1, currentSize+1);
    }

    /**
     * 获取当前最大堆数据量
     * */
    public Integer heapSize(){
        return currentSize;
    }

    public void buildHeap(){
        for (int i = currentSize/2; i > 0; i--){
            percoateDown(i);
        }
    }


    /**
     * 二叉堆中添加节点（上滤方法）
     * */
    public void insert(AnyType x){
        if(currentSize == array.length){
            enlargArray(currentSize*2+1);
        }
        int hole = ++currentSize;
        for(array[0] = x; x.compareTo(array[hole/2]) > 0;hole = hole/2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    /**
     * 查找最小值，二叉堆最小值是根节点
     * */
    public AnyType findMax(){
        return array[1];
    }


    /**
     * 删除最小值（根节点）
     * */
    public AnyType deleteMax(){
        if(isEmpty()){
            return null;
        }
        AnyType max = findMax();
        array[1] = array[currentSize--];
        array[currentSize + 1] = null;
        percoateDown(1);
        return max;
    }

    /**
     * 判断是否为空
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
     * 置空整个二叉堆
     * */
    public void makeEmpty(){
        array = new AnyType[DEFAULT_CAPACITY];
    }


    /**
     * 将顶部较大的节点逐步下滤
     * */
    public void percoateDown(int hole){
        int child=0;
        AnyType tem = array[hole];
        for (;hole*2<=currentSize;hole=child){
            child = 2*hole;
            //此处需要添加array[child+1]，因为我们是默认每个父节点都满子节点，那么必然遇见单个子节点情况，此时我们compareTo方法对比null对象返回的-1，这个是为了方便min情况时候，可以看到min情况时候无需增加这个条件，因此此处有不同用来兼容null节点情况。
            if(child<=currentSize && array[child].compareTo(array[child+1]) < 0 && array[child+1] != null){
                child ++;
            }
            if(tem.compareTo(array[child]) < 0){
                array[hole] = array[child];
            }else {
                break;
            }
        }
        array[hole] = tem;
    }


    /**
     * 扩容
     * */
    public void enlargArray(int newSize){
        AnyType[] newArray = new AnyType[newSize];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public static void main(String[] args) {
        Integer size = 10;
        BinaryHeapMax binaryHeap = new BinaryHeapMax((size + 2 )*11/10);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            AnyType anyType = new AnyType(random.nextInt(100));
            binaryHeap.insert(anyType);
        }
        while (!binaryHeap.isEmpty()){
            System.out.println(binaryHeap.deleteMax().getElement());
        }
    }
}
