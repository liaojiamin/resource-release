package com.ljm.resource.myproxy.jdkproxy;

/**
 * Created by jiamin5 on 2023/3/12.
 */
public class Dog implements Person {
    @Override
    public void eat(String name) {
        System.out.println("dog eat " + name);
    }

    @Override
    public void drink() {
        System.out.println("dog drink kele");
    }
}
