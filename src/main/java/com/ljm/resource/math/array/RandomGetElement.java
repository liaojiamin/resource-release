package com.ljm.resource.math.array;

import java.util.Random;

/**
 * 随机取出数组中元素，要求不重复取
 * @author liaojiamin
 * @Date:Created in 16:26 2021/10/23
 */
public class RandomGetElement {

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            Integer ele = random.nextInt(100);;
            array[i] = ele;
            System.out.print(ele+",");
        }
        System.out.println();
        getElement(array);
    }

    public static void getElement(int[] array){
        if(array == null || array.length <=0 ){
            return;
        }
        Random random = new Random();
        Integer size = array.length;
        Integer number = size;
        while (number >0){
            Integer index = random.nextInt(size);
            Integer temp = array[index];
            array[index] = array[size-1];
            array[size -1] = temp;
            System.out.println(temp);
            number --;
        }

    }
}
