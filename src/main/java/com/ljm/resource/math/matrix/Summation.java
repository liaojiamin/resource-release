package com.ljm.resource.math.matrix;

/**
 * @author liaojiamin
 * @Date:Created in 14:49 2021/7/8
 */
public class Summation {
    private static int sum;
    public void setNumber(int result){
        sum = result;
    }

    public Summation(int result, int number){
        setNumber(result);
        sum+=number;
    }

    public int getNumber(){
        return sum;
    }

    public static void main(String[] args) {
        int s1 =  new Summation(0,1).getNumber();
        int s2 =  new Summation(s1,2).getNumber();
        int s3 =  new Summation(s2,3).getNumber();
        int s4 =  new Summation(s3,4).getNumber();
        int s5 =  new Summation(s4,5).getNumber();
        int s6 =  new Summation(s5,6).getNumber();
        int s7 =  new Summation(s6,7).getNumber();
        int s8 =  new Summation(s7,8).getNumber();
        int s9 =  new Summation(s8,9).getNumber();
        int s10 =  new Summation(s9,10).getNumber();
        int s11 =  new Summation(s10,11).getNumber();
        System.out.println(s7);
    }


}
