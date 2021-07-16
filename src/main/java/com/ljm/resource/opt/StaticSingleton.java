package com.ljm.resource.opt;

/**
 * @author liaojiamin
 * @Date:Created in 14:56 2020/3/23
 */
public class StaticSingleton {
    private StaticSingleton(){
        System.out.println("StaticSingleton is create");
    }
    private static class SingletionHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletionHolder.instance;
    }
}
