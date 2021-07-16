package com.ljm.resource.math.array;

/**
 * @author liaojiamin
 * @Date:Created in 17:59 2021/6/28
 */
public class FindShowOnceInArray {

    public static void main(String[] args) {
        int[] array = new int[]{2,998,3,77,3,2,5,5};
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result ^= array[i];
        }
        int[] resultTemp = findOnceNumON(array);
        System.out.println(resultTemp.length+ ": "+ resultTemp[0] + ": "+ resultTemp[1]);
    }

    /**
     * 数组中只有一个数单个存在，其他都有两个找出这个单数
     * */
    public static int findOne(int[] array){
        if(array == null){
            return 0;
        }
        int resultInt = 0;
        for (int i = 0; i < array.length; i++) {
            resultInt ^= array[i];
        }
        return resultInt;
    }

    /**
     * O(n) 空间复杂度
     * 一个数组中有两个数单个存在，其他的都是成对存在，找出这两个数
     * */
    public static int[] findOnceNumON(int[] array){
        if(array == null || array.length <= 2){
            return array;
        }
        int resultInt = 0;
        for (int i = 0; i < array.length; i++) {
            resultInt^=array[i];
        }
        System.out.println(resultInt);
        int position = checkoutPosition(resultInt);
        System.out.println(position);
        int positionOne = 0;
        int positionNotOne = 0;
        int positionNum = 1;
        positionNum <<=position;
        System.out.println(positionNum);
        for (int i = 0; i < array.length; i++) {
            if((array[i]&positionNum) == positionNum){
                positionOne^=array[i];
            }else {
                positionNotOne^=array[i];
            }
        }
        int[] resultTemp = new int[2];
        resultTemp[0]= positionOne;
        resultTemp[1] = positionNotOne;
        return resultTemp;
    }

    /**
     * 一个数组中有两个数单个存在，其他的都是成对存在，找出这两个数
     * */
    public static int[] findOnceNum(int[] array){
        if(array == null || array.length <= 2){
            return array;
        }
        int resultInt = 0;
        for (int i = 0; i < array.length; i++) {
            resultInt^=array[i];
        }
        System.out.println(resultInt);
        int position = checkoutPosition(resultInt);
        System.out.println(position);
        int[] arrayIsone = new int[array.length];
        int positionOne = 0;
        int[] arrayNotOne = new int[array.length];
        int positionNotOne = 0;
        int positionNum = 1;
        positionNum <<=position;
        System.out.println(positionNum);
        for (int i = 0; i < array.length; i++) {
            if((array[i]&positionNum) == positionNum){
                arrayIsone[positionOne] = array[i];
                positionOne++;
            }else {
                arrayNotOne[positionNotOne] = array[i];
                positionNotOne++;
            }
        }
        int[] resultTemp = new int[2];
        int oneResult = 0;
        int twoResult = 0;
        for (int i = 0; i < positionOne; i++) {
            oneResult^=arrayIsone[i];
        }
        resultTemp[0]=oneResult;
        for (int i = 0; i < positionNotOne; i++) {
            twoResult^=arrayNotOne[i];
        }
        resultTemp[1] = twoResult;
        return resultTemp;
    }



    /**
     * 找到目标数据二进制数第一位为1 的位置
     *
     */
    public static int checkoutPosition(int resultInt){
        int position = 0;
        for(;resultInt > 0; resultInt>>=1){
            if((resultInt&1) == 1){
                return position;
            }
            position++;
        }
        return position;

    }
}
