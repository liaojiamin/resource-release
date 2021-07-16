package com.ljm.resource.math.array;

import com.ljm.resource.math.binary.AnyType;
import com.ljm.resource.math.binary.BinaryHeap;
import com.ljm.resource.math.binary.BinaryHeapMax;

import java.util.Arrays;
import java.util.Random;

/**
 * 找出数组中最小的K个数
 *
 * @author liaojiamin
 * @Date:Created in 10:18 2021/6/16
 */
public class GetLeastNumbers {

    public static void main(String[] args) {
        Integer[] arrayData = new Integer[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arrayData[i] = random.nextInt(50);
            System.out.print(arrayData[i]+",");
        }
        System.out.println();
        Integer[] heapArray = getLeastNumbersByBinaryHeap(arrayData, 10);
        for (int i = 0; i < heapArray.length; i++) {
            System.out.print(heapArray[i]+",");
        }
        System.out.println();
        Integer[] newArray = getLeastNumbers(arrayData, 10);
        for (Integer integer : newArray) {
            System.out.print(integer+",");
        }
        System.out.println();
        Integer[] maxArray = getLeastNumberByBinaryHeapMax(arrayData, 10);
        for (Integer integer : maxArray) {
            System.out.print(integer+",");
        }
    }

    /**
     * 利用二叉堆，最大堆，处理海量数据情况下获取前k个最小的数据
     * */
    public static Integer[] getLeastNumberByBinaryHeapMax(Integer[] array, Integer key){
        if (array == null || array.length <= 0 || array.length < key) {
            return new Integer[]{};
        }
        if (array.length == key) {
            return array;
        }
        Integer size = (array.length+2)*11/10;
        BinaryHeapMax binaryHeapMax = new BinaryHeapMax(size);
        for (int i = 0; i < array.length; i++) {
            AnyType anyType = new AnyType(array[i]);
            if(binaryHeapMax.heapSize() >= key){
                Integer heapMax =  Integer.valueOf(binaryHeapMax.findMax().getElement().toString());
                if(array[i] < heapMax){
                    binaryHeapMax.deleteMax();
                    binaryHeapMax.insert(anyType);
                }
            }else {
                binaryHeapMax.insert(anyType);
            }

        }
        AnyType[] anyTypes = binaryHeapMax.getAppHeapData();
        Integer[] result = new Integer[key];
        for (int i = 0; i < anyTypes.length; i++) {
            result[i] = Integer.valueOf(anyTypes[i].getElement().toString());
        }
        return result;
    }

    /**
     * 利用二叉堆，最小堆的结构特性，构建最小堆后，每次取跟节点都是最小的节点
     * 循环取k个最小堆中根元素，得到我们的结果
     * */
    public static Integer[] getLeastNumbersByBinaryHeap(Integer[] array, Integer key){
        if (array == null || array.length <= 0 || array.length < key) {
            return new Integer[]{};
        }
        if (array.length == key) {
            return array;
        }
        Integer size = (array.length + 2 )*11/10;
        BinaryHeap binaryHeap = new BinaryHeap(size);
        for (int i = 0; i < array.length; i++) {
            binaryHeap.insert(new AnyType(array[i]));
        }
        Integer[] result = new Integer[key];
        for (Integer i = 0; i < key; i++) {
            result[i] = Integer.valueOf(binaryHeap.deleteMin().getElement().toString());
        }
        return result;

    }

    /**
     * 按快速排序思路，找到第k个大的数，将小的放k前面
     * 将大的放k后，得到数组中最小的k个数就是下标是0~k的所有数
     */
    public static Integer[] getLeastNumbers(Integer[] array, Integer key) {
        if (array == null || array.length <= 0 || array.length < key) {
            return new Integer[]{};
        }
        if (array.length == key) {
            return array;
        }
        return quickSort(array, 0, array.length-1, key);

    }

    public static Integer[] quickSort(Integer[] array, Integer left, Integer right, Integer key) {
        if (left < right) {
            Integer middle = quickSortSwap(array, left, right);
            if (middle == key) {
                return Arrays.copyOfRange(array, 0, key);
            }
            quickSort(array, left, middle - 1, key);
            quickSort(array, middle + 1, right, key);
        }
        return Arrays.copyOfRange(array, 0, key);
    }

    public static Integer quickSortSwap(Integer[] array, Integer left, Integer right) {
        if (left < right) {
            Integer position = array[left];
            while (left < right) {
                while (left < right && array[right] > position) {
                    right--;
                }
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                while (left < right && array[left] < position) {
                    left++;
                }
                if(left < right){
                    array[right] = array[left];
                    right --;
                }
            }
            array[left] = position;
        }
        return left;
    }
}










































