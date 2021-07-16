package com.ljm.resource.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * @author liaojiamin
 * @Date:Created in 11:06 2020/4/14
 */
public class Worker implements Runnable {
    protected Queue<Object> workQueue;
    protected Map<String, Object> resultMap;

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务处理逻辑，子类中实现具体逻辑
    public Object handle(Object input) {
        return input;
    }

    @Override
    public void run() {
        while (true) {
            //获取子任务
            Object input = workQueue.poll();
            if (input == null) {
                break;
            }
            //处理子任务
            Object re = handle(input);
            //处理结果写入结果集
            resultMap.put(Integer.toString(input.hashCode()), re);
        }
    }
}
