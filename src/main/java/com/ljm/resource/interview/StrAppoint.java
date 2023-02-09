package com.ljm.resource.interview;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jiamin5 on 2023/2/2.
 */
public class StrAppoint {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
//        String str  = new String("abc");
//        System.out.println(changeStrValue(str));
//        strCompare();
//        stringBufferOrStringBuilder();
        copyOnWriteDemo();
    }

    /**
     * copyonwriterArrayLIst
     * */
    public static void copyOnWriteDemo(){
        CopyOnWriteArrayList cwArrayList = new CopyOnWriteArrayList();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            cwArrayList.add(random.nextInt(20+i));
        }
        cwArrayList.remove(0);
        for (Object o : cwArrayList) {
            System.out.println(o);
        }
    }

    /**
     * 不修改原字符串引用，做到修改字符串值目的
     * */
    public static String changeStrValue(String str) throws NoSuchFieldException, IllegalAccessException {
        Field field = str.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(str, "abcd".toCharArray());
        return str;
    }

    public static void strCompare(){
        String str_1 = new String("abc");
        String str_2 = "abc";
        System.out.println(str_1 == str_2);
        System.out.println(str_2 == str_1.intern());
    }


    /**
     * stringBuffer 线程安全，用synchronized
     * stringBuilder 非线程安全，单线程效率高
     * */
    public static void stringBufferOrStringBuilder() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer("123");
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuffer.append("a");
                }
            });
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuffer.append("b");
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuffer.append("c");
                }
            });
            thread.start();
            thread1.start();
            thread2.start();
        }
        StringBuilder stringBuilder = new StringBuilder("123");
        for (int i = 0; i < 10; i++) {
            Thread thread4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuilder.append("d");
                }
            });
            Thread thread5 = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuilder.append("e");
                }
            });
            Thread thread6 = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringBuilder.append("f");
                }
            });
            thread4.start();
            thread5.start();
            thread6.start();
        }

        Thread.sleep(2000);
        System.out.println(stringBuffer.toString());
        System.out.println(stringBuilder.toString());
    }
}
