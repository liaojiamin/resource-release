package com.ljm.resource.opt.myobserver;

/**
 * @author liaojiamin
 * @Date:Created in 16:19 2020/3/24
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notivfyAllObserver();
}
