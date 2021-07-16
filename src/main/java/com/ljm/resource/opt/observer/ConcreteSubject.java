package com.ljm.resource.opt.observer;

import java.awt.*;
import java.util.Vector;

/**
 * @author liaojiamin
 * @Date:Created in 15:03 2020/3/24
 */
public class ConcreteSubject implements ISubject {
    //维护观察者队列
    Vector<IObserver> observers = new Vector<>();

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void inform() {
        Event evt = new Event();
        for (IObserver observer : observers) {
            observer.update(evt);
        }
    }
}
