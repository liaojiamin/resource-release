package com.ljm.resource.nio;

/**
 * @author liaojiamin
 * @Date:Created in 10:55 2020/4/10
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanreliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString(){
        return "i am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj  is null");
        }else {
            System.out.println("obj is alieve");
        }
        System.out.println("second gc");
//        obj = null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj  is null");
        }else {
            System.out.println("obj is alieve");
        }
    }
}
