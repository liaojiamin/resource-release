package com.ljm.resource.opt;

import javassist.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liaojiamin
 * @Date:Created in 17:00 2020/3/23
 */
public class CglibDBQueryInsterceptor implements MethodInterceptor {
    IDBQuery real = null;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(real == null){
            real = new DBQueryImpl();
        }
        return real;
    }
    public static IDBQuery createCGlibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDBQueryInsterceptor());
        enhancer.setInterfaces(new Class[]{enhancer.createClass()});
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }
    public static IDBQuery createJavassistBytecodeDynamicProxy()
            throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool mpool = new ClassPool(true);
        CtClass mCtc = mpool.makeClass(IDBQuery.class.getName() + "JavassistBytecodeProxy");
        mCtc.addInterface(mpool.get(IDBQuery.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addField(CtField.make("public "+IDBQuery.class.getName()+ " real;", mCtc));
        String dbqueryname = DBQueryImpl.class.getName();
        mCtc.addMethod(CtNewMethod.make("public String request(){if(real == null) real = new "
                + dbqueryname + "(); return real.request();}",mCtc));
        Class pc = mCtc.toClass();
        IDBQuery bytecodeProxy = (IDBQuery) pc.newInstance();
        return bytecodeProxy;
    }
}
