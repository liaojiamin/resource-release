package com.ljm.resource.guarded;

import com.ljm.resource.thread.FutureData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 14:52 2020/4/14
 */
public class ClientThread extends Thread {
    private RequestQueue requestQueue;
    private List<Request> myRequest = new ArrayList<>();

    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestId: " + i + "Thread name " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            request.setResponse(new FutureData());
            requestQueue.addRequest(request);
            myRequest.add(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Request request1 : myRequest) {
                System.out.println("ClientThread name is: " + Thread.currentThread().getName() + "Reponse is : " + request.getResponse().getResult());
            }
        }
    }
}
