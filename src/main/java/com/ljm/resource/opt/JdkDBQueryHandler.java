package com.ljm.resource.opt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liaojiamin
 * @Date:Created in 16:44 2020/3/23
 */
public class JdkDBQueryHandler implements InvocationHandler {
    IDBQuery real = null;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(real == null){
            real = new DBQueryImpl();
        }
        return real;
    }
    public static IDBQuery createJdkproxy(){
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IDBQuery.class},new JdkDBQueryHandler());
        return jdkProxy;
    }
}
