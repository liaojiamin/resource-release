package com.ljm.resource.guarded;

import com.ljm.resource.thread.Data;

/**
 * @author liaojiamin
 * @Date:Created in 14:43 2020/4/14
 */
public class Request {
    private String name;
    private Data response;
    public Request(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public synchronized Data getResponse() {
        return response;
    }
    public synchronized void setResponse(Data response) {
        this.response = response;
    }
}
