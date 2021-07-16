package com.ljm.resource.opt;

/**
 * @author liaojiamin
 * @Date:Created in 15:11 2020/3/23
 */
public class LazySingleton {
    private LazySingleton(){
        System.out.println("LazySingleton is create");
    }
    private static LazySingleton instance = null;
    public static LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if(instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
