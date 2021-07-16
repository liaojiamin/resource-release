package com.ljm.resource.masterworker;

/**
 * @author liaojiamin
 * @Date:Created in 13:51 2020/4/14
 */
public class PlusWorker extends Worker {
    @Override
    public Object handle(Object input){
        Integer i = (Integer) input;
        return i*i*i;
    }
}
