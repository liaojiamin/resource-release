package com.ljm.resource.myproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jiamin5 on 2023/3/12.
 */
public class MyProxy {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Class[] interfaces = dog.getClass().getInterfaces();
        Person proxyPersion = (Person)Proxy.newProxyInstance(dog.getClass().getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("洗手");
                Object obj =  method.invoke(dog, args);
                System.out.println("洗头");
                return obj;
            }
        });
        proxyPersion.eat("面包");
    }
}
