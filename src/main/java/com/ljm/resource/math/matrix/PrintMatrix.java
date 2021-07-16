package com.ljm.resource.math.matrix;

/**
 * 顺时针打印矩阵
 * @author liaojiamin
 * @Date:Created in 11:11 2021/4/2
 */
public class PrintMatrix {
    public static void printMartix(int[][] nums, int col, int row){
        if(nums == null || row <= 0 || col <= 0){
            return;
        }
        int start = 0;
        while(col > start*2 && row > start*2){
            printMartix(nums, col, row, start);
            start ++;
        }
    }

    public static void printMartix(int[][] nums, int col, int row, int start){
        int endx = col-start-1;
        int endy = row-start-1;

        for (int i = start; i <= endx; i++) {
            System.out.print(nums[start][i]);
            System.out.print(",");
        }
        if(start < endy){
            for(int i = start+1; i<=endy; i++){
                System.out.print(nums[i][endx]);
                System.out.print(",");
            }
        }
        if(start < endx && start< endy){
            for(int i = endx-1;i >=start; i--){
                System.out.print(nums[endy][i]);
                System.out.print(",");
            }
        }
        if(start < endx){
            for(int i = endy - 1; i>=start+1;i--){
                System.out.print(nums[i][start]);
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args) {
        int row = 10, col = 8;
        int[][] nums = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int i1 = 0; i1 < col; i1++) {
                nums[i][i1] = i*i1;
                System.out.print(i*i1);
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println();
        printMartix(nums, col, row);
    }
}

