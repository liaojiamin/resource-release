package com.ljm.resource.thread;

/**
 * @author liaojiamin
 * @Date:Created in 11:51 2020/4/13
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData futureData = new FutureData();
        new Thread(){
            @Override
            public void run(){
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
