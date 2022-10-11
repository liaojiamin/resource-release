package com.ljm.resource.math.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 10:53 2020/11/5
 */
public class BubbleSort {
    public static int[] getArrayData(int size) {
        int[] arrayData = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int temp = random.nextInt(50);
            if (temp > 0) {
                arrayData[i] = temp;
            } else {
                int value = temp - 2 * temp;
                arrayData[i] = value;
            }

        }
        return arrayData;
    }

    /**
     * @author: liaojiamin
     * @description: 从小到大基数排序
     * @date: 16:13 2020/11/23
     */
    public static int[] radixSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        int max = arrayData[0];
        for (int i = 0; i < arrayData.length; i++) {
            if (arrayData[i] > max) {
                max = arrayData[i];
            }
        }
        int temp = max;
        int maxDigit = 0;
        int i = 10;
        while (temp > 0) {
            temp /= i;
            i *= 10;
            maxDigit++;
        }
        int mod = 10;
        int dev = 1;
        for (int j = 0; j < maxDigit; j++, dev *= 10, mod *= 10) {
            //这个地方可以优化，先获取最大桶的大小，就可以不用每个桶都arrayData.length
            int[][] tempArray = new int[10][arrayData.length];
            int[] tempIndex = new int[10];
            for (int k = 0; k < arrayData.length; k++) {
                int bucket = (arrayData[k]%mod)/dev;
                if(arrayData[k] > 0){
                    tempArray[bucket][tempIndex[bucket]++] = arrayData[k];
                }else {
                    tempArray[bucket][tempIndex[bucket]++] = -1;
                }
            }
            int index = 0;
            for (int s = 0; s < tempIndex.length; s++) {
                if (tempIndex[s] <= 0){
                    continue;
                }
                for (int i1 = 0; i1 < tempIndex[s]; i1++) {
                    if( tempArray[s][i1] > 0){
                        arrayData[index] = tempArray[s][i1];
                    }else {
                        arrayData[index] = 0;
                    }
                    index++;
                }
            }
        }
        return arrayData;
    }

    /**
     * 时间复杂度O(n), 空间复杂度O(n+k)
     * 从小到大桶排序
     *
     * @author: liaojiamin
     * @date: 18:09 2020/11/16
     */
    public static int[] bucketSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        int pos = arrayData.length;
        //默认十个桶
        int bucketSize = 10;
        if (pos <= 1) {
            return arrayData;
        }
        int min = arrayData[0];
        int max = arrayData[0];
        for (int i = 0; i < pos; i++) {
            if (arrayData[i] < min) {
                min = arrayData[i];
            }
            if (arrayData[i] > max) {
                max = arrayData[i];
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        //二维数组一纬标识桶， 二维存放数字，最差情况所有数字在统一个桶中
        int[][] bucket = new int[bucketCount][pos];
        //统计最终桶中数据个数，做游标position作用，指定改桶下一个数据存放位置
        int[] index = new int[bucketCount];
        for (int i = 0; i < pos; i++) {
            int num = (arrayData[i] - min) / bucketSize;
            //将 第num个桶的第index[index] 个数赋值
            bucket[num][index[num]++] = arrayData[i];
        }
        int position = 0;
        for (int i = 0; i < bucket.length; i++) {
            //对每一个进行插入排序
            insertionSort(bucket[i]);
            for (int j = bucket[i].length - index[i]; j < bucket[i].length; j++) {
                arrayData[position++] = bucket[i][j];
            }
        }
        return arrayData;

    }

    /**
     * 时间复杂度O(n+k), 空间复杂度O(n+k)
     * 从小到大计数排序
     *
     * @author: liaojiamin
     * @date: 18:09 2020/11/16
     */
    public static int[] countSortCompaNegative(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        int minValue = arrayData[0];
        int maxvalue = arrayData[0];
        for (int i = 0; i < arrayData.length; i++) {
            if (arrayData[i] > maxvalue) {
                maxvalue = arrayData[i];
            }
            if (arrayData[i] < minValue) {
                minValue = arrayData[i];
            }
        }
        //全正数情况
        if (minValue >= 0) {
            return countSort(arrayData, maxvalue, false);
        }
        //全负数情况
        if (maxvalue <= 0) {
            return countSort(arrayData, Math.abs(minValue), true);
        }
        //正负兼有情况
        return countSortAll(arrayData, minValue, maxvalue);
    }

    public static int[] countSortAll(int[] arrayData, int minValue, int maxvalue) {
        int[] nagative = new int[Math.abs(minValue) + 1];
        int[] positive = new int[maxvalue + 1];
        for (int i = 0; i < arrayData.length; i++) {
            if (arrayData[i] > 0) {
                int temp = positive[arrayData[i]];
                temp += 1;
                positive[arrayData[i]] = temp;
            } else {
                int nagativePosition = Math.abs(arrayData[i]);
                int temp = nagative[nagativePosition];
                temp += 1;
                nagative[nagativePosition] = temp;
            }
        }
        int position = 0;
        for (int i = nagative.length - 1; i >= 0; i--) {
            if (nagative[i] > 0) {
                for (int j = 0; j < nagative[i]; j++) {
                    int value = i - 2 * i;
                    arrayData[position++] = value;
                }
            }
        }
        for (int i = 0; i < positive.length; i++) {
            if (positive[i] > 0) {
                for (int j = 0; j < positive[i]; j++) {
                    arrayData[position++] = i;
                }
            }
        }
        return arrayData;
    }

    public static int[] countSort(int[] arrayData, int maxValue, boolean isNegative) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        int[] countArray = new int[maxValue + 1];
        for (int i = 0; i < arrayData.length; i++) {
            int value = Math.abs(arrayData[i]);
            int temp = countArray[value];
            temp += 1;
            countArray[value] = temp;
        }
        int position = 0;
        if (isNegative) {
            position = arrayData.length - 1;
        } else {
            position = 0;
        }
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > 0) {
                for (int j = 0; j < countArray[i]; j++) {
                    if (isNegative) {
                        //负数
                        int value = i - 2 * i;
                        arrayData[position--] = value;
                    } else {
                        arrayData[position++] = i;
                    }
                }
            }
        }
        return arrayData;
    }


    public static int[] countSort(int[] arrayData, int maxValue) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        int[] countArray = new int[maxValue];
        for (int i = 0; i < arrayData.length; i++) {
            int temp = countArray[arrayData[i]];
            temp += 1;
            countArray[arrayData[i]] = temp;
        }
        int position = 0;
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > 0) {
                for (int j = 0; j < countArray[i]; j++) {
                    arrayData[position++] = i;
                }
            }
        }
        return arrayData;
    }


    /**
     * 时间复杂度平均O(nlog2^n) 空间复杂度O(nlog2^n)
     * 从小到大快速排序
     */
    public static int[] quickSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        quickSort(arrayData, 0, arrayData.length - 1);
        return arrayData;
    }

    public static void quickSort(int[] arrayData, int left, int right) {
        if (left < right) {
            int temp = swap2(arrayData, left, right);
            quickSort(arrayData, left, temp - 1);
            quickSort(arrayData, temp + 1, right);
        }
    }

    public static int swap2(int[] arrayData, int left, int right) {
        if (left < right) {
            int positionData = arrayData[left];
            while (left < right) {
                //从后先前找一个小于基准值positionData的数据
                while (right > left && arrayData[right] > positionData) {
                    right--;
                }
                if (left < right) {
                    arrayData[left] = arrayData[right];
                    left++;
                }
                //从前向后找一个大于基准值的数据
                while (left < right && arrayData[left] < positionData) {
                    left++;
                }
                if (left < right) {
                    arrayData[right] = arrayData[left];
                    right--;
                }
            }
            arrayData[left] = positionData;
        }
        return left;
    }

    public static void swap(int[] arrayData, int left, int right) {
        if (left < right) {
            int privot = left;
            int index = privot + 1;
            for (int i = index; i <= right; i++) {
                if (arrayData[i] < arrayData[privot]) {
                    int temp = arrayData[i];
                    arrayData[i] = arrayData[privot];
                    arrayData[privot] = temp;
                }
            }
            int temp = arrayData[privot];
            arrayData[privot] = arrayData[index - 1];
            arrayData[index - 1] = temp;

            swap(arrayData, left, index - 2);
            swap(arrayData, index, right);
        }


    }

    /**
     * 时间复杂度O(nlogn) 空间复杂度O(n)
     * 从小到大，归并排序
     */
    public static int[] mergeSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        if (arrayData.length < 2) {
            return arrayData;
        }
        int middle = arrayData.length / 2;
        int[] left = Arrays.copyOfRange(arrayData, 0, middle);
        int[] right = Arrays.copyOfRange(arrayData, middle, arrayData.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        if (left.length == 2 || left.length == 3) {
            left = bubbleSort(left);
        }
        if (right.length == 2 || right.length == 3) {
            right = bubbleSort(right);
        }
        int leftPosition = left.length - 1;
        int rightPosition = right.length - 1;
        int resultPosition = result.length - 1;
        while (resultPosition >= 0) {
            if (rightPosition < 0 || (leftPosition >= 0 && left[leftPosition] > right[rightPosition])) {
                result[resultPosition--] = left[leftPosition--];
            } else {
                result[resultPosition--] = right[rightPosition--];
            }
        }
        return result;
    }

    /**
     * 时间复杂度 O(n^2) 空间复杂度O(1)
     * 从小到大希尔排序
     */
    public static int[] shellSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        for (int gap = arrayData.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrayData.length; i++) {
                //此处i 在分组的第一组数据的最后一个数据 i+1 第二组，i+2第三组，依次类推
                //此处j 从该分组数据的最后一个数据向前遍历，遇到更大的值就交换
                // （用gap来区分分组，与插入排序一模一样，只不过简单插入排序间隔是i--，此处是i-=gap）
                for (int j = i; j - gap >= 0; j -= gap) {
                    if (arrayData[j - gap] > arrayData[j]) {
                        int temp = arrayData[j];
                        arrayData[j] = arrayData[j - gap];
                        arrayData[j - gap] = temp;
                    }
                }
            }
        }
        return arrayData;
    }

    /**
     * 时间复杂度 O(n^2) 空间复杂度O(1)
     * 从小到大插入排序
     */
    public static int[] insertionSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        for (int i = 1; i < arrayData.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arrayData[j - 1] > arrayData[j]) {
                    int temp = arrayData[j - 1];
                    arrayData[j - 1] = arrayData[j];
                    arrayData[j] = temp;
                }
            }
        }
        return arrayData;
    }


    /**
     * 时间复杂度 O(n^2) 空间复杂度O(1)
     * 从小到大排序冒泡排序
     */
    public static int[] bubbleSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        for (int i = 0; i < arrayData.length; i++) {
            for (int j = 0; j < arrayData.length - 1; j++) {
                if (arrayData[j] > arrayData[i]) {
                    int temp = arrayData[j];
                    arrayData[j] = arrayData[i];
                    arrayData[i] = temp;
                }
            }
        }
        return arrayData;
    }

    /**
     * 时间复杂度 O(n^2) 空间复杂度O(1)
     * 从小到大排序冒泡排序
     */
    public static int[] selectionSort(int[] arrayData) {
        if (null == arrayData || arrayData.length <= 1) {
            return arrayData;
        }
        for (int i = 0; i < arrayData.length; i++) {
            int last = arrayData[i];
            int position = i;
            for (int j = i + 1; j < arrayData.length; j++) {
                if (last > arrayData[j]) {
                    last = arrayData[j];
                    position = j;
                }
            }
            int temp = arrayData[i];
            arrayData[i] = arrayData[position];
            arrayData[position] = temp;
        }
        return arrayData;
    }

    public static void main(String[] args) {
        int[] beginArrayData = getArrayData(10);
        int[] arrayData = quickSort(beginArrayData);
        for (int i = 0; i < arrayData.length; i++) {
            System.out.println(arrayData[i]);
        }
    }
}
