package com.ljm.resource.math.array;

/**
 * 给定一个数据k，判断K是否在矩阵 s 中，并且给出在s中的位置
 * @author liaojiamin
 * @Date:Created in 13:59 2020/10/30
 */
public class FindOne {
    public static Integer[][] getTwoDimenArray(int rows, int cloumns){
        Integer[][] twoDimen = new Integer[rows][cloumns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cloumns; j++) {
                Integer key = (i+1) * (j+1);
                twoDimen[i][j] = key;
                System.out.print(key + "  ");
            }
            System.out.println("");
        }
        return twoDimen;
    }

    /**
     * 错误示范，二分查找
     * */
    public static void findOneInTwoDimenArray1(int rows, int cloumns, Integer key){
        if(rows <=0 || cloumns <=0){
            return;
        }
        Integer[][] twoDimen = getTwoDimenArray(rows, cloumns);
        if(twoDimen == null){
            return;
        }
        boolean isInArray = false;
        int keyCloumns = -1;
        int keyRows = -1;

        int left = 0;
        int right = rows * cloumns -1;
        int col = cloumns;
        while (left <= right){
            int mid = (left + right) / 2;
            Integer value = twoDimen[mid/ col][mid%col];//中间位置
            if(value == key){
                keyRows = mid/col;
                keyCloumns = mid%col;
                isInArray = true;
                break;
            }else if(value < key){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        System.out.println("isInArray: "+ isInArray + "  position: rows:" +keyRows + " cloums:" + keyCloumns);
    }
    public static void findOneInTwoDimenArray2(int rows, int cloumns, Integer key){
        if(rows <=0 || cloumns <=0 || key == null){
            return;
        }
        Integer[][] twoDimen = getTwoDimenArray(rows, cloumns);
        if(twoDimen == null){
            return;
        }
        boolean isInArray = false;
        int keyCloumns = cloumns -1;
        int keyRows =0;
        while (keyCloumns >= 0 && keyRows < rows){
            if(twoDimen[keyRows][keyCloumns] == key){
                System.out.println("isInArray: "+ isInArray + "  position: rows:" +keyRows + " cloums:" + keyCloumns);
                break;
            }else if(twoDimen[keyRows][keyCloumns] > key){
                keyCloumns --;
            }else {
                keyRows ++;
            }
        }
    }

    public static void findOneInTwoDimenArray(int rows, int cloumns, Integer key){
        if(rows <=0 || cloumns <=0){
            return;
        }
        Integer[][] twoDimen = getTwoDimenArray(rows, cloumns);
        if(twoDimen == null){
            return;
        }
        boolean isInArray = false;
        int keyCloumns = -1;
        int keyRows = -1;
        for (int i = cloumns-1; i >= 0; i--) {
            if(twoDimen[0][i] == key){
                keyRows = 0;
                keyCloumns = i;
                isInArray = true;
                break;
            }
            if(twoDimen[0][i] < key){
                keyCloumns = i;
                break;
            }
        }
        if(!isInArray){
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < keyCloumns; j++) {
                    if(twoDimen[i][j] == key){
                        keyRows = i;
                        keyCloumns = j;
                        isInArray = true;
                        break;
                    }
                }
                if(isInArray) {
                    break;
                }
            }
        }
        System.out.println("isInArray: "+ isInArray + "  position: rows:" +keyRows + " cloums:" + keyCloumns);

    }

    public static void main(String[] args) {
        findOneInTwoDimenArray2(5, 4, null);
    }
}
