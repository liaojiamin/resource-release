package com.ljm.resource.guarded;

import com.ljm.resource.thread.FutureData;
import com.ljm.resource.thread.RealData;

/**
 * @author liaojiamin
 * @Date:Created in 14:48 2020/4/14
 */
public class ServerThread extends Thread {
    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();
            final FutureData futureData = (FutureData) request.getResponse();
            RealData realData = new RealData(request.getName());
            futureData.setRealData(realData);
            System.out.println(Thread.currentThread().getName() + " handless " + request);
        }
    }
}
