package com.ljm.resource.jvm;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * Created by jiamin5 on 2023/3/3.
 */
public class JavaBeanObject {
    private String name = "java";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
