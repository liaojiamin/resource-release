package com.ljm.resource.kilim;

/**
 * @author liaojiamin
 * @Date:Created in 17:39 2020/4/24
 */
public class MainTest {
    public static void test1(){
        {
            byte[] b = new byte[6*1204*1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }
    public static void main(String[] args) {
        test1();
    }
}
