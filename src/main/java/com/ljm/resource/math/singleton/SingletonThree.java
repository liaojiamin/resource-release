package com.ljm.resource.math.singleton;

/**
 * @author liaojiamin
 * @Date:Created in 14:40 2020/10/27
 */
public class SingletonThree {
    private SingletonThree(){}
    private static SingletonThree singletonThree = new SingletonThree();
    public static SingletonThree getInstance(){
        return singletonThree;
    }

    public static void main(String[] args) {
        System.out.println("test");
        SingletonThree singletonThree = SingletonThree.getInstance();
        System.out.println(singletonThree.toString());
    }
}
