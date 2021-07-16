package com.ljm.resource.math.dynamic;

/**
 * 获取斐波那契数列
 * @author liaojiamin
 * @Date:Created in 12:06 2021/2/2
 */
public class Fibonacci {

    /**
     * 递归实现
     * */
    public Long addFrom1N(long n){
        return n <= 0 ? 0 : n + addFrom1N(n-1);
    }
    /**
     * 循环实现
     * */
    public long addForm1N_interative(long n){
        if(n <=0){
            return 0;
        }
        long result= 0;
        for (long i = 1; i < n; i++) {
            result +=i;
        }
        return result;
    }
    /**
     * f(n) = f(n-1) + f(n-2)
     * n > 2
     * n=1 : f(1) = 1
     * n=2 : f(2) = 1
     * f(3) = f(1) + f(2) = 1 + 1 = 2;
     */
    public static Long getFibonacci(int n) {
        if (n <= 0L) {
            return 0L;
        }
        if (n == 1L || n == 2L) {
            return 1L;
        }
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }


    /**
     * 动态规划求值
     * */
    public static Long getFibonacciGreat(int n) {
        if (n <= 0L) {
            return 0L;
        }
        if (n == 1L || n == 2L) {
            return 1L;
        }
        Long answer = 1L;
        Long last = 1L;
        Long nextLast = 1L;
        for (Long i = 0L; i < n - 2; i++) {
            answer = last + nextLast;
            nextLast = last;
            last = answer;
        }
        return answer;
    }

    /**
     * 矩阵求值
     * */
    public static Matrix2By2 matrixMultiply(Matrix2By2 matrix1, Matrix2By2 matrix2){
        return new Matrix2By2(
                matrix1.getM_00()*matrix2.getM_00() + matrix1.getM_01()*matrix2.getM_10(),
                matrix1.getM_00()*matrix2.getM_01() + matrix1.getM_01()*matrix2.getM_11(),
                matrix1.getM_10()*matrix2.getM_00() + matrix1.getM_11()*matrix2.getM_10(),
                matrix1.getM_10()*matrix2.getM_01() + matrix1.getM_11()*matrix2.getM_11());
    }

    public static Matrix2By2 matrixPower(int n){
        if( n<= 0){
            return new Matrix2By2();
        }
        Matrix2By2 matrix = new Matrix2By2();
        if(n==1){
            matrix = new Matrix2By2(1,1,1,0);
        }
        else if(n%2 == 0){
            matrix = matrixPower(n/2);
            matrix = matrixMultiply(matrix, matrix);
        }
        else if(n%2 == 1){
            matrix = matrixPower((n-1)/2);
            matrix = matrixMultiply(matrix, matrix);
            matrix = matrixMultiply(matrix, new Matrix2By2(1,1,1,0));
        }
        return matrix;
    }

    public static long getFibonacciBest(int n){
        if(n == 0){
            return 0;
        }
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        Matrix2By2 powerNminus2 = matrixPower(n-1);
        return powerNminus2.getM_00();
    }

    public static void main(String[] args) {
        System.out.println("-------------------------1");
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(getFibonacci(40));
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println("-------------------------2");
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(getFibonacciGreat(40));
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println("-------------------------3");
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(getFibonacciBest(40));
        System.out.println(System.currentTimeMillis()/1000);
    }
}
