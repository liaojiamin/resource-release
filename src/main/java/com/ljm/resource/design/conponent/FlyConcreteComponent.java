package com.ljm.resource.design.conponent;

/**
 * @author liaojiamin
 * @Date:Created in 14:56 2022/8/4
 */
public class FlyConcreteComponent implements FlyConponent {
    @Override
    public void flay() {
        System.out.println("to fly");
    }
}
