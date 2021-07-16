package com.ljm.resource.math.matrix;

/**
 * @author liaojiamin
 * @Date:Created in 15:16 2021/7/8
 */
public enum  SummationRecursion {
    LAST_4(4){
        @Override
        public int mySumResult(){
            return this.getSummation() + LAST_3.mySumResult();
        }
    },
    LAST_3(3){
        @Override
        public int mySumResult(){
            return this.getSummation() + LAST_2.mySumResult();
        }
    },
    LAST_2(2){
        @Override
        public int mySumResult(){
            return this.getSummation() + LAST_1.mySumResult();
        }
    },
    LAST_1(1){
        @Override
        public int mySumResult(){
            return 1;
        }
    };
    private int summation;

    public int getSummation() {
        return summation;
    }

    public void setSummation(int summation) {
        this.summation = summation;
    }

    SummationRecursion(int summation){
        this.summation = summation;
    }
    public abstract int mySumResult();

    public static void main(String[] args) {
        System.out.println(SummationRecursion.LAST_4.mySumResult());
    }
}
