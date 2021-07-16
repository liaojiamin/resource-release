package com.ljm.resource.math.singleton;

/**
 * @author liaojiamin
 * @Date:Created in 14:38 2020/10/27
 */
public class SingletonTwo {
    private SingletonTwo(){}
    public static SingletonTwo singletonTwo;

    public static SingletonTwo getInstance(){
        if(null == singletonTwo){
            synchronized (SingletonOne.class){
                if(null == singletonTwo){
                    singletonTwo = new SingletonTwo();
                }
            }
        }
        return singletonTwo;
    }
}
