package com.ljm.resource.producerconsumer;

/**
 * @author liaojiamin
 * @Date:Created in 16:13 2020/4/14
 */
public class PCData {
    private final int intData;

    public PCData(int d ){
        intData = d;
    }
    public PCData(String d){
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
