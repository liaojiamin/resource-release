package com.ljm.resource.opt;

/**
 * @author liaojiamin
 * @Date:Created in 15:07 2020/3/23
 */
public class SingletonStr {
    private SingletonStr(){
        System.out.println("SingletonStr is create");
    }
    private static SingletonStr instance = new SingletonStr();
    public static SingletonStr getInstance(){
        return instance;
    }
    public static void createString(){
        System.out.println("createString in singleton");
    }

    public static void main(String[] args) {
        SingletonStr.createString();
    }
}
