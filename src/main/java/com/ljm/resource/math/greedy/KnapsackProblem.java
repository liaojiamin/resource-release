package com.ljm.resource.math.greedy;

import java.util.Random;

/**
 * 近似装箱问题 - 联机算法 - 脱机算法
 *
 * @author liaojiamin
 * @Date:Created in 14:12 2021/1/15
 */
public class KnapsackProblem {

    private static final int MAX_WEIGHT = 10;

    /**
     * 随机生成10 以内的物品数据
     */
    public static int[] getSource(int num) {
        int[] source = new int[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            source[i] = random.nextInt(10);
            System.out.print(source[i] + "， ");
        }
        System.out.println();
        return source;
    }

    /**
     * 最佳合适算法
     * 将物品放入一个能容纳他并且最满的箱子中
     * */
    public static int[][] multiKnasackBest(int[] source ){
        int num = source.length;
        int[][] myPackage = new int[num][num];
        int[] position = new int[num];
        int[] weight = new int[num];
        int packagePosition = 0;
        for (int i = 0; i < source.length; i++) {
            int maxWeight = -1;
            int maxWeightId = -1;
            for (int j = 0; j <= packagePosition; j++) {
                if(MAX_WEIGHT - weight[j] > source[i]){
                    if(weight[j] > maxWeight){
                        maxWeight = weight[j];
                        maxWeightId = j;
                    }
                }
            }
            if(maxWeightId < 0){
                packagePosition++;
                myPackage[packagePosition][position[packagePosition]] = source[i];
                weight[packagePosition] += source[i];
                position[packagePosition] += 1;
            }else {
                myPackage[maxWeightId][position[maxWeightId]] = source[i];
                weight[maxWeightId] += source[i];
                position[maxWeightId] += 1;
            }
        }
        return myPackage;
    }

    public static int[][] multiknasackDecreasing(int[] source){
        quickSort(source, 0, source.length - 1);
        return multiKnasackFirst(source);
    }

    /**
     * 快排从大到小
     * */
    public static void quickSort(int[] source, int left, int right){
        if(left > right){
            int temp = swap(source, left, right);
            quickSort(source, left, temp - 1);
            quickSort(source, temp + 1, right);
        }
    }

    public static int swap(int[] source, int left, int right){
        if (left < right){
            int position = source[left];
            while (left < right){

                while(left < right && position > source[right]){
                    right --;
                }
                if(left < right){
                    source[right] = source[left];
                    left ++;
                }
                while (left < right && position < source[left]){
                    left ++;
                }
                if(left < right){
                    source[left] = source[right];
                    right --;
                }
            }
            source[left] = position;
        }
        return left;
    }




    /**
     * 首次适合算法：
     * 义序草庙箱子，并把新的一项物品放入能容纳的箱子中
     * */
    public static int[][] multiKnasackFirst(int[] source ){
        int num = source.length;
        int[][] myPackage = new int[num][num];
        int[] position = new int[num];
        int[] weight = new int[num];
        int packagePosition = 0;
        for (int i = 0; i < source.length; i++) {
            boolean isInsert = false;
            for (int j = 0; j < weight.length; j++) {
                if(MAX_WEIGHT - weight[j] >= source[i]){
                    isInsert = true;
                    myPackage[j][position[j]] = source[i];
                    weight[j] += source[i];
                    position[j] += 1;
                    break;
                }
            }
            if(!isInsert){
                packagePosition ++;
                myPackage[packagePosition][position[packagePosition]] = source[i];
                weight[packagePosition] += source[i];
                position[packagePosition] += 1;
            }
        }
        return myPackage;
    }

    /**
     * 下项适合算法：
     * 当处理任何一项物品时候，我们坚持是否能装进去刚才装物品的同一个箱子
     * 如果能，就放入，如果不能就重新开一个新箱
     */
    public static int[][] multiKnasack(int[] source ) {
        int num = source.length;
        //极端情况，所有获取都是1 ，都在第一个里面
        //每个箱子存储的物品详情
        int[][] myPackage = new int[num][num];
        //第n个箱子下一个物品位置
        int[] position = new int[num];
        //第n个箱子已存储重量
        int[] weight = new int[num];
        int packagePosition = 0;
        for (int i = 0; i < source.length; i++) {
            if (MAX_WEIGHT - weight[packagePosition] < source[i]) {
                packagePosition++;
            }
            myPackage[packagePosition][position[packagePosition]] = source[i];
            weight[packagePosition] += source[i];
            position[packagePosition] += 1;

        }
        return myPackage;
    }


    public static void main(String[] args) {
        int[] source = getSource(20);
        int[][] myPackage = multiKnasack(source);
        for (int i = 0; i < myPackage.length; i++) {
            System.out.print("第" + i + "个箱子：");
            for (int i1 = 0; i1 < myPackage[i].length; i1++) {
                if (myPackage[i][i1] > 0) {
                    System.out.print(myPackage[i][i1] + ", ");
                }
            }
            System.out.println();
        }
    }
}
