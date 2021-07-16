package com.ljm.resource.math.singleton;

/**
 * @author liaojiamin
 * @Date:Created in 15:45 2020/10/27
 */
public class SingletonFour {
    private SingletonFour(){}
    private static class SingletonInstance{
        static SingletonFour singletonFour = new SingletonFour();
    }
    public static SingletonFour getInstance(){
        return SingletonInstance.singletonFour;
    }
    public static void main(String[] args) {
        System.out.println("test");
        SingletonFour singletonFour = SingletonFour.getInstance();
        System.out.println(singletonFour.toString());
    }
}
