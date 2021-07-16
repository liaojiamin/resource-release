package com.ljm.resource.math.array;

/**
 * 如果数组中超过数组一半的数都是同一个数，找出那个数字
 * @author liaojiamin
 * @Date:Created in 17:21 2021/5/27
 */
public class MoreThenHalfNum {

    public static void main(String[] args) {
        int[] arrayData = {4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
        System.out.println(findThenHalfNum(arrayData));
//        System.out.println(findMiddleNum(arrayData, 0, arrayData.length -1));
//        arrayData = quickSort(arrayData);
//        for (int arrayDatum : arrayData) {
//            System.out.print(arrayDatum + ",");
//        }
    }

    public static int[] quickSort(int[] arrayData){
        if(arrayData == null || arrayData.length <= 1){
            return arrayData;
        }
        return quickSort(arrayData, 0, arrayData.length -1);
    }

    /**
     * 二分查找示例
     * */
    public static int[] quickSort(int[] arrayData, Integer left, Integer right){
        if(left < right){
            int index = swapArray(arrayData, left, right);
            quickSort(arrayData, left, index - 1);
            quickSort(arrayData, index + 1, right);
        }
        return arrayData;
    }

    /**
     * 方法二：依次遍历数组中每一个数字，将遇到的数字保存到 k 对象中，如果依次遇到的数字中与k中相同统计+1
     *  否则 -1，如果统计为0 情况k对象，依次对每个数字操作，最终保留下来的比如是数量最多的那个数字
     * */
    public static int findThenHalfNum(int[] arrayData){
        if(arrayData == null || arrayData.length <= 1){
            return -1;
        }
        int findNum = -1;
        int findNumCount = -1;
        for (int i = 0; i < arrayData.length; i++) {
            if(findNum == -1){
                findNum = arrayData[i];
                findNumCount = 1;
            }else {
                if(findNumCount == 0){
                    findNum = arrayData[i];
                    findNumCount =1;
                }else {
                    if(findNum == arrayData[i]){
                        findNumCount ++;
                    }else {
                        findNumCount --;
                    }
                }

            }
        }
        return findNum;
    }


    /**
     *  方法一：二分查找思想找出 数组中间大小的那个数字
     *  如果该数字在数组中的个数超过数组的一半，那么中间大小的数字必然是这个数字
     * */
    public static int findMiddleNum(int[] arrayData, int left, int right){
        if(arrayData == null || arrayData.length <= 1){
            return -1;
        }
        Integer middle = (left + right)/2;
        if(left < right){
            int index = swapArray(arrayData, left, right);
            if(index == middle){
                return arrayData[middle];
            }
            quickSort(arrayData, left, index - 1);
            quickSort(arrayData, index + 1, right);
        }
        return arrayData[middle];
    }

    /**
     * 挖坑法二分查找
     * */
    public static int swapArray(int[] arrayData, Integer left, Integer right){
        if(left < right){
            int position = arrayData[left];
            while (left < right){
                while (right > left && arrayData[right] > position){
                    right --;
                }
                if(left < right){
                    arrayData[left] = arrayData[right];
                    left ++;
                }
                while (left < right && arrayData[left] < position){
                    left ++;
                }
                if(left < right){
                    arrayData[right] = arrayData[left];
                    right--;
                }
            }
            arrayData[left] = position;
        }
        return left;
    }
}
