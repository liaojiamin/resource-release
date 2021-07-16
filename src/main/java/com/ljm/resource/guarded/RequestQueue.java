package com.ljm.resource.guarded;

import java.util.LinkedList;

/**
 * @author liaojiamin
 * @Date:Created in 14:43 2020/4/14
 */
public class RequestQueue {
    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Request) queue.remove();
    }

    public synchronized void addRequest(Request request) {
        queue.add(request);
        notifyAll();
    }
}
