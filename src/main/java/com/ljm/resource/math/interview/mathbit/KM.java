package com.ljm.resource.math.interview.mathbit;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jiamin5 on 2023/3/23.
 * 一个数组中有 一种数出现两k次，其他的数字都出现两 M次，找出这个K，1 <= K < M
 * 要求时间复杂度O(n)空间复杂度O(1)
 */
public class KM {
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int onlyKTimes(int[] arr, int k, int m) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        //统计每一个数二进制为中1 的位置，并且将出现1 的位置累加
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
//                if(((num >> i) & 1 ) != 0){
//                    t[i] ++;
//                }
                t[i] += ((num >> i) & 1);
            }
        }
        int kNum = 0;
        //在累加数组中，如果第k位的数据是 m的整数倍，说明这个位置只有 m次的数字出现过1 ，否则就是k次的数字
        //找到每一个k次的数字中1 出现的位置，通过1 << i 得到单前位置的1 与 0 做 "|"操作复原K次出现的数
        for (int i = 0; i < 32; i++) {
            if (t[i] % m == 0) {
                continue;
            }
            // k % m = k
            // (k + N*m) % m = k
            if (t[i] % m == k) {
                kNum |= (1 << i);
            } else {
                return -1;
            }
        }
        if (kNum == 0) {
            int countZero = 0;
            for (int i : arr) {
                if (i == 0) {
                    countZero++;
                }
            }
            if (countZero != k) {
                return -1;
            }
        }
        return kNum;
    }


    // 更简洁的写法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = k;
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // 为了测试
    // [-range, +range]
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    // 为了测试
    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 5000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
//            int ans3 = km(arr, k, m);
//            if (ans1 != ans2 || ans1 != ans3) {
            if (ans1 != ans2 ) {
                System.out.println(ans1);
//                System.out.println(ans3);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
