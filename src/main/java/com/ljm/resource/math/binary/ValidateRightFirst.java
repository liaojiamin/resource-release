package com.ljm.resource.math.binary;

/**
 *  判断给定序列是否二叉查找树后续遍历 5,7,6,9,11,10,8
 * @author liaojiamin
 * @Date:Created in 18:22 2021/4/2
 */
public class ValidateRightFirst {

    public static boolean validateRightList(int[] binaryNodeList){
        if(binaryNodeList == null || binaryNodeList.length <= 1){
            return false;
        }
        return validateRightList(binaryNodeList, 0, binaryNodeList.length-1);
    }

    public static boolean validateRightList(int[] binaryNodeList, int start, int end){
        if(binaryNodeList == null|| start >= end){
            return false;
        }
        int root = binaryNodeList[end];
        int middlePosition = 0;
        for (int i = start; i < end-1; i++) {
            if(binaryNodeList[i] > root){
                middlePosition = i;
                break;
            }
        }
        for(int i = middlePosition; i< end-1;i++ ){
            if(binaryNodeList[i] < root){
                return false;
            }
        }
        boolean left = true;
        if(middlePosition > 0){
            left = validateRightList(binaryNodeList, start, middlePosition-1);
        }
        boolean right = true;
        if(middlePosition < end -1){
            right = validateRightList(binaryNodeList, middlePosition, end-1);
        }
        return left& right;
    }

    public static void main(String[] args) {
        int[] binaryNodeList = {5,7,6,9,11,10,8};
        System.out.println(validateRightList(binaryNodeList));
    }
}
