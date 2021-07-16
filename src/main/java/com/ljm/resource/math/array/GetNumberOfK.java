package com.ljm.resource.math.array;

/**
 * 查询有序数组中数字k 出现的次数
 *
 * @author liaojiamin
 * @Date:Created in 16:08 2021/6/24
 */
public class GetNumberOfK {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 33, 44, 55, 56, 56, 56, 56, 56, 57};
        System.out.println(countK(array, 0));
        System.out.println(binarySearchK(array, 0));
        System.out.println(binarySearchAllK(array, 0));
    }


    /**
     * 方法一，遍历统计 O(n)
     */
    public static Integer countK(int[] array, int k) {
        if (array == null || array.length <= 0) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法二：二分查找找出k，得到k后，左右遍历k，直到找到，firstK和lastK
     * O(n)
     */
    public static Integer binarySearchK(int[] array, int k) {
        Integer positionK = binarySearch(array, 0, array.length - 1, k);
        if (positionK < 0) {
            return -1;
        }
        Integer firstK = positionK;
        Integer lastK = positionK;
        for (int i = positionK; i < array.length; i++) {
            if (array[i] == k) {
                lastK = i;
            }
        }
        for (int i = positionK; i >= 0; i--) {
            if (array[i] == k) {
                firstK = i;
            }
        }
        return lastK - firstK + 1;
    }

    public static Integer binarySearch(int[] array, int left, int right, int k) {
        if (array == null || left < 0 || right > array.length - 1 || left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] == k) {
            return middle;
        }
        if (array[middle] > k) {
            return binarySearch(array, left, middle - 1, k);
        }
        if (array[middle] < k) {
            return binarySearch(array, middle + 1, right, k);
        }
        return -1;
    }

    /**
     * 方法三，还是二分查找，分别找出firstK， lastK
     */
    public static Integer binarySearchAllK(int[] array, int k) {
        if (array == null || array.length <= 0) {
            return -1;
        }
        int firstK = binarySearchFirstK(array, 0, array.length - 1, k);
        int lastK = binarySearchLastK(array, 0, array.length - 1, k);
        if (firstK < 0 && lastK < 0) {
            return -1;
        }
        return lastK - firstK + 1;
    }

    /**
     * 二分查找第一个k
     */
    public static Integer binarySearchFirstK(int[] array, int left, int right, int k) {
        if (array == null || left < 0 || right > array.length - 1 || left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] == k) {
            if (middle - 1 >= left && array[middle - 1] == k) {
                return binarySearchFirstK(array, left, middle - 1, k);
            } else {
                return middle;
            }
        }
        if (array[middle] < k) {
            return binarySearchFirstK(array, middle + 1, right, k);
        }
        if (array[middle] > k) {
            return binarySearchFirstK(array, left, middle - 1, k);
        }
        return -1;
    }

    /**
     * 二分查找最后一个k
     */
    public static Integer binarySearchLastK(int[] array, int left, int right, int k) {
        if (array == null || left < 0 || right > array.length - 1 || left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] == k) {
            if (middle + 1 <= right && array[middle + 1] == k) {
                return binarySearchLastK(array, middle + 1, right, k);
            } else {
                return middle;
            }
        }
        if (array[middle] > k) {
            return binarySearchLastK(array, left, middle - 1, k);
        }
        if (array[middle] < k) {
            return binarySearchLastK(array, middle + 1, right, k);
        }
        return -1;
    }


}
