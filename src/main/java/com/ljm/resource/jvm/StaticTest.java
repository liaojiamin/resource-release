package com.ljm.resource.jvm;

/**
 * Created by jiamin5 on 2023/3/6.
 */
public class StaticTest {
    static class Parent{
        public static int A = 1;
        static {
            A = 2;
        }
    }
    static class Sub extends Parent{
        public static  int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
