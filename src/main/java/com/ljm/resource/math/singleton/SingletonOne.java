package com.ljm.resource.math.singleton;

/**
 * @author liaojiamin
 * @Date:Created in 14:33 2020/10/27
 */
public class SingletonOne {
    private SingletonOne(){}
    public static SingletonOne singletonOne;
    public static SingletonOne getInstance(){
        synchronized (SingletonOne.class){
            if(null == singletonOne){
                singletonOne = new SingletonOne();
            }
            return singletonOne;
        }
    }
}
