package com.ljm.resource.design.adapt;

/**
 * @author liaojiamin
 * @Date:Created in 12:17 2022/8/4
 */
public class Bird extends Flying implements FlyTarget {

    @Override
    public void canFly() {
        System.out.println("i am bird");
        fly();
        System.out.println("over flay,i am walk");
    }
}
