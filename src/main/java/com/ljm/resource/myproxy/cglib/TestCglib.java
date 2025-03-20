package com.ljm.resource.myproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by jiamin5 on 2023/3/12.
 */
public class TestCglib {
    public static void main(String[] args) {
        TestCglib testCglib = new TestCglib();
        testCglib.testCglin();
    }
    public void testCglin(){
        //获取一个person的代理对象
        Person person = new Person();
        //获取一个Enhancer 对象
        Enhancer enhancer = new Enhancer();
        //设置父类字节码
        enhancer.setSuperclass(person.getClass());
        //获取MethodInterceptor
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
               // Object obj ：生成之后的代理对象personProxy
                // Method method：父类中原本要执行的方法 Persion >>> eat()
                // Object[] args：方法在调用时候传入的实参数
                // MethodProxy proxy：子类中重写父类的方法personProxy >>> eat();
                Object res = null;
                if(method.getName().equals("eat")){
                    System.out.println("洗手");
                    res = proxy.invokeSuper(obj, args);
                    System.out.println("刷网");
                }
                return res;
            }
        };
        //设置回调函数：methodInterceptor
        enhancer.setCallback(methodInterceptor);
        //获得代理对象
        Person personProxy = (Person) enhancer.create();
        //使用代理对象完成功能
        personProxy.eat("面包");
    }

    static class Person{
        public Person(){}
        public void eat(String foodName){
            System.out.println("eat some 面包 和" + foodName);
        }
    }
}
