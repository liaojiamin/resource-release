package com.ljm.resource.nio;

/**
 * @author liaojiamin
 * @Date:Created in 10:02 2020/4/10
 */
public class MyObject {
    @Override
    protected  void finalize() throws Throwable {
        super.finalize();
        //被GC回收会调用finalize
        System.out.println("MyObject's finalize called");
    }

    @Override
    public String toString(){
        return "i am MyObject";
    }
}
