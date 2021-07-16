package com.ljm.resource.math.dynamic;

/**
 * 求解递推关系的值： C(N) =(2/N)(C(0) + C(1) + C(2) + ... C(N-1)) + N , 其中C(0) = 1
 * @author liaojiamin
 * @Date:Created in 15:29 2021/2/2
 */
public class Eval {

    public static double getEval(int n){
        if(n == 0){
            return 1;
        }
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += getEval(i);
        }
        return 2*sum/n + n;
    }

    public static double getEvalGreat(int n){
        double[] evalArray = new double[n+1];
        evalArray[0] = 1;
        for (int i = 1; i < evalArray.length; i++) {
           double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += evalArray[j];
            }
            evalArray[i] = 2*sum/i + i;
        }
        return evalArray[n];
    }

    public static void main(String[] args) {
        System.out.println(getEvalGreat(1));
        System.out.println(getEvalGreat(2));
        System.out.println(getEvalGreat(3));
        System.out.println(getEvalGreat(4));
        System.out.println(getEvalGreat(5));
        System.out.println(getEvalGreat(6));
        System.out.println(getEvalGreat(10));
        System.out.println(getEvalGreat(100));
    }
}
