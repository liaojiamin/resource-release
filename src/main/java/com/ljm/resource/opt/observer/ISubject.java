package com.ljm.resource.opt.observer;

/**
 * @author liaojiamin
 * @Date:Created in 15:01 2020/3/24
 */
public interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void inform();
}
