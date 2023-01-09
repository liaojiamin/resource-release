package com.ljm.resource.math.array;

import java.util.Random;

/**
 * Created by jiamin5 on 2022/12/5.
 */
public class FindNumberIn2DArray {

    public static int findNumberIn2DArray(int[][] targetArray, int targetNum) {
        int j = 0;
        int i = targetArray.length - 1;
        while (i>=0 && j <= targetArray[0].length-1) {
            if (targetArray[j][i] < targetNum) {
                j++;
                continue;
            }
            if (targetArray[j][i] > targetNum) {
                i--;
                continue;
            }
            return targetArray[j][i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int [][] targetArray = new int[5][5];
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            for (int i1 = 0; i1 < 5; i1++) {
                int thisNum = random.nextInt(50);
                targetArray[i][i1] = thisNum;
                System.out.print(thisNum+", ");
            }
            System.out.println();
        }
        System.out.println(findNumberIn2DArray(targetArray, 4));
    }
}
