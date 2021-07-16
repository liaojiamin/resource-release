package com.ljm.resource.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liaojiamin
 * @Date:Created in 11:09 2020/4/14
 */
public class Master {
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    //worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<>();
    //子任务处理结果集
    protected Map<String, Object> resultMap = new ConcurrentHashMap<>();

    //所有子任务结束
    public boolean isComplete(){
        for (Map.Entry<String, Thread> stringThreadEntry : threadMap.entrySet()) {
            if(stringThreadEntry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //Master的构造，需要一个Worker进程逻辑，需要Worker进程数
    public Master(Worker worker, int countWroker){
        worker.setResultMap(resultMap);
        worker.setWorkQueue(workQueue);
        for (int i = 0; i < countWroker; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }
    //提交一个任务
    public void submit(Object obj){
        workQueue.add(obj);
    }
    //返回子任务结果集
    public Map<String, Object> getResultMap(){
        return resultMap;
    }

    //运行所有worker进程，进行处理
    public void execute(){
        for (Map.Entry<String, Thread> stringThreadEntry : threadMap.entrySet()) {
            stringThreadEntry.getValue().start();
        }
    }

}
