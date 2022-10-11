package com.ljm.resource.math.array;

import java.util.*;

/**
 * 八皇后问题
 *
 * @author liaojiamin
 * @Date:Created in 15:03 2021/5/26
 */
public class EightQueen {
    private static final List<Integer[]> myQueen = new ArrayList<>();

    public static void main(String[] args) {
//        Integer[] eight = {0,1,2,3,4,5,6,7};
//        getEightQueenPerMutain(eight, 0);
//        List<Integer[]> realQueen = checkQueen(myQueen);
//        for (Integer[] integers : realQueen) {
//            for (int i = 0; i < integers.length; i++) {
//                System.out.print(integers[i]+",");
//            }
//            System.out.println();
//        }
//        System.out.println(realQueen.size());

        for (int i = 0; i < 8; i++) {
            Integer[] eight = new Integer[8];
            getEightQueen(eight, i);
        }
//        getEightQueenRetro();
        print();
    }

    public static void print() {
        for (Integer[] integers : myQueen) {
            for (int i = 0; i < integers.length; i++) {
                System.out.print(integers[i] + ",");
            }
            System.out.println();
        }
        System.out.println(myQueen.size());
    }

    /**
     * 回朔递归
     */
    public static void getEightQueenRetro() {
        Integer[] eight = new Integer[8];
        check(eight, 0);

    }


    public static void check(Integer[] eight, int n) {
        if (n == eight.length) {
            Integer[] findQueen = Arrays.copyOf(eight, eight.length);
            myQueen.add(findQueen);
            return;
        }
        for (int i = 0; i < eight.length; i++) {
            eight[n] = i;
            if(judge(eight, n)){
                check(eight, n+1);
            }
        }
    }


    public static boolean judge(Integer[] eight, int n){
        for(int i=0;i<n;i++){
            if(eight[i] == eight[n] || Math.abs(n-i) == Math.abs(eight[n] - eight[i])){
                return false;
            }
        }
        return true;
    }


    /**
     * 穷举+筛选
     * 直接获取八皇后问题最终结果
     */
    public static void getEightQueen(Integer[] queen, int start) {
        for (int i = 0; i < 8; i++) {
            queen[start] = i;
            if (checkout(queen, start)) {
                if (start == queen.length - 1) {
                    Integer[] realQueen = new Integer[8];
                    for (int i1 = 0; i1 < queen.length; i1++) {
                        realQueen[i1] = queen[i1];
                    }
                    myQueen.add(realQueen);
                } else {
                    getEightQueen(queen, start + 1);
                }
            }
        }
    }

    /**
     * 校验是否符合八皇后问题规则
     */
    public static boolean checkout(Integer[] queen, Integer end) {
        Set<Integer> checkRepeat = new HashSet<>();
        for (int i = 0; i <= end; i++) {
            if (checkRepeat.contains(queen[i])) {
                return false;
            }
            checkRepeat.add(queen[i]);
            for (int j = i + 1; j <= end; j++) {
                if (queen[i] == null || queen[j] == null) {
                    return false;
                }
                if ((i - j) == (queen[i] - queen[j]) || (j - i) == (queen[i] - queen[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查所有可能的八皇后排列，筛选出符合条件的：
     * i-j == integer[i] - integer[j] || j-i = integer[i]-integer[j] 情况的都是统一对角的
     */
    public static List<Integer[]> checkQueen(List<Integer[]> myQueen) {
        List<Integer[]> realEightQueen = new ArrayList<>();
        for (Integer[] integers : myQueen) {
            boolean isCheck = true;
            for (int i = 0; i < integers.length; i++) {
                if (!isCheck) {
                    break;
                }
                for (int j = i + 1; j < integers.length; j++) {
                    if ((i - j) == (integers[i] - integers[j])
                            || (j - i) == (integers[i] - integers[j])) {
                        isCheck = false;
                        break;
                    }
                }

            }
            if (isCheck) {
                realEightQueen.add(integers);
            }
        }
        return realEightQueen;
    }


    /**
     * 八皇后问题排列解决方案
     */
    public static void getEightQueenPerMutain(Integer[] eight, Integer start) {
        if (eight == null || eight.length <= 1 || start == eight.length - 1) {
            Integer[] newEight = new Integer[eight.length];
            for (int i = 0; i < eight.length; i++) {
                newEight[i] = eight[i];
            }
            myQueen.add(newEight);
        }
        for (int i = start; i <= eight.length - 1; i++) {
            Integer temp = eight[start];
            eight[start] = eight[i];
            eight[i] = temp;
            getEightQueenPerMutain(eight, start + 1);
            temp = eight[start];
            eight[start] = eight[i];
            eight[i] = temp;
        }
    }
}
