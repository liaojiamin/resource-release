package com.ljm.resource.math.singleton;

/**
 * @author liaojiamin
 * @Date:Created in 10:18 2020/10/27
 */
public class Singleton {
    private Singleton(){}
    public static Singleton singleton;
    public static Singleton getInstance(){
        if(null == singleton){
            singleton = new Singleton();
        }
        return singleton;
    }
}
