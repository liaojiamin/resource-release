package com.ljm.resource.jvm;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * Created by jiamin5 on 2023/3/3.
 */
public class JavaBeanGCTest {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            CtClass cc = ClassPool.getDefault().makeClass("Geym" + i);
            cc.setSuperclass(ClassPool.getDefault().get("com.ljm.jvm.JavaBeanObject"));
            Class clz = cc.toClass();
            JavaBeanObject v = (JavaBeanObject) clz.newInstance();
        }
    }
}
