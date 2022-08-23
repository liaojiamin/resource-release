package com.ljm.resource.math.bitmatch;

/**
 * @author liaojiamin
 * @Date:Created in 16:30 2021/3/17
 */
public class NumberOfBit {
    /**
     * 正整数n 二进制表示中 1 的个数
     * */
    public static int numberOf1(int n){
        int count = 0;
        while (n > 0){
            int andOne = n & 1;
            if(andOne == 1){
                count ++;
            }
            n = n>>1;
        }
        return count;
    }

    /**
     * 负数n 二进制表示中 1 个数
     * */
    public static int numberOf1_1(int n){
        int count = 0;
        int flag = 1;
        while (flag > 0){
            if((n & flag) > 0){
                count ++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 打印 1 ~ n位最大整数，
     * ex： n=3， 1~999
     * */
    public static void print1ToMaxOfNDigits(int n){
        int number = 1;
        int i = 0;
        while(i++ < n){
            number *=10;
        }
        for (int i1 = 0; i1 < number; i1++) {
            System.out.println(i1);
        }
    }

    /**
     * 打印 1 ~ n位最大整数，
     * ex： n=3， 1~999
     * */
    public static void print1ToMaxOfNDigitsByChars(int n){
        if(n<=0){
            return;
        }
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        while (!increment(number)){
            printCharArr(number);
        }
    }



    /**
     * 字符模拟加法
     */
    public static boolean increment(char[] chars){
        boolean isOverflow = false;
        int takeOver = 0;
        for (int i = chars.length-1; i >= 0; i--) {
            //添加进位，初始为0不影响
            int nSum = chars[i] - '0' + takeOver;
            //+1 操作
            if(i == chars.length-1 ){
                nSum ++;
            }
            if(nSum >= 10){
                if(i==0){
                    isOverflow = true;
                }else {
                    nSum -= 10;
                    takeOver = 1;
                    chars[i] = (char) ('0' + nSum);
                }
            }else {
                chars[i] = (char)('0' + nSum);
                break;
            }
        }
        return isOverflow;
    }

    /**
     * 打印数字，取出首字母中'0'
     * */
    public static void printCharArr(char[] chars){
        if(chars == null || chars.length <= 0){
            return;
        }
        boolean begin = true;
        for (int i = 0; i < chars.length; i++) {
            if(begin && chars[i] != '0'){
                begin = false;
            }
            if(!begin){
                System.out.print(chars[i]);
            }
        }
        System.out.println();
    }
    /**
     * 递归打印1 ~ 最大n位整数
     * */
    public static void print1ToMaxOfNDigitsRecursively(int n){
        if(n <= 0){
            return;
        }
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        for (int i = 0; i < 10; i++) {
            number[0] = (char)(i+'0');
            print1ToMaxOfNDigitsRecursively(number, 0);
        }
    }

    public static void print1ToMaxOfNDigitsRecursively(char[] number, int index){
        if(index == number.length -1){
            printCharArr(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index + 1] = (char)('0' + i);
            print1ToMaxOfNDigitsRecursively(number, index +1);
        }
    }

    public static int numberOf1_2(int n){
        int count = 0;
        while (n != 0){
            n = n&(n-1);
            if(n != 0){
                count ++;
            }
        }
        return count;
    }

    //字母转数字  A-Z ：1-26
    public static int letterToNumber(char letter) {
        int num = (int)(letter - 'A' + 1) ;
        return num;
    }

    public static Long base26(String baseStr){
        if(baseStr == null){
            return -1L;
        }
        char[] charArray = baseStr.toCharArray();
        Long baseNumber = 26L;
        Long resultNum = 0L;
        double position = 0;
        for (int i = charArray.length -1; i >=0; i--) {
            Double temp = Math.pow(baseNumber, position) * letterToNumber(charArray[i]);
            resultNum += temp.longValue();
            position++;
        }
        return resultNum;
    }

    /**
     * 求解一个数base 的exponent次幂
     * */
    public static double power(double base, int exponent){
        if(base == 0 || base == 1){
            return base;
        }
        if(exponent == 0){
            return 1d;
        }
        if(exponent == 1){
            return base;
        }
        double result = 1.0;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        if(exponent > 0){
            return result;
        }
        return 1/result;
    }
    /**
     * 实现Math.pow(a,b)
     * */
    public static double powerWithUnsignedExponent(double base, int exponent){
        if(base == 0 || base == 1){
            return base;
        }
        if(exponent == 0){
            return 1d;
        }
        if(exponent == 1){
            return base;
        }
        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result*=result;
        if((exponent & 1) == 1){
            result*=base;
        }
        return result;
    }

    /**
     * 浮点数判断是否相等
     * */
    public static boolean equals(double num, double num1){
        if((num - num1 > -0.00000001) && (num - num1) < 0.00000001 ){
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
//        System.out.println(power(7d,31));
//        System.out.println(powerWithUnsignedExponent(2d,31));
        print1ToMaxOfNDigitsRecursively(5);
//        System.out.println(base26("AA"));
//        print1ToMaxOfNDigits(3);
//        System.out.println(base26("AB"));
//        System.out.println(base26("AZ"));
//        System.out.println(base26("ZZ"));
//        System.out.println(base26("AAA"));
//        System.out.println('9' - '8');
//        System.out.println((char)('3' + 1));
    }
}
