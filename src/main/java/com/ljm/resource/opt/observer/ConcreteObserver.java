package com.ljm.resource.opt.observer;

/**
 * @author liaojiamin
 * @Date:Created in 15:13 2020/3/24
 */
public class ConcreteObserver implements IObserver {
    @Override
    public void update(Event evt) {
        System.out.println("Observer receives imformation");
    }
}
