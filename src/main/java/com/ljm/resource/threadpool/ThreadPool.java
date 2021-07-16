package com.ljm.resource.threadpool;

import java.util.List;
import java.util.Vector;

/**
 * @author liaojiamin
 * @Date:Created in 10:33 2020/4/15
 */
public class ThreadPool {
    private static ThreadPool instance = null;
    //空闲队列
    private List<PThread> idleThreads;
    //线程总数
    private int threadCounter;
    private boolean isShutDown = false;

    private ThreadPool(){
        this.idleThreads = new Vector<>(5);
        threadCounter = 0;
    }
    public int getCreatedThreadsCount(){
        return threadCounter;
    }
    //获取线程池实例
    public synchronized static ThreadPool getInstance(){
        if(instance == null){
            instance = new ThreadPool();
        }
        return instance;
    }

    //结束池中所有线程
    public synchronized void shutDown(){
        isShutDown = true;
        for (int i = 0; i < idleThreads.size(); i++) {
            PThread idleThread = (PThread) idleThreads.get(i);
            idleThread.shutDown();
        }
    }
    //将线程放入池中
    protected synchronized void repool(PThread repoolingThread){
        if(!isShutDown){
            idleThreads.add(repoolingThread);
        }else {
            repoolingThread.shutDown();
        }
    }
    //执行任务
    public synchronized void start(Runnable target){
        PThread thread = null;
        //有空闲闲置至今获取最后一个使用
        if(idleThreads.size() > 0){
            int lastIndex = idleThreads.size() - 1;
            thread = (PThread) idleThreads.get(lastIndex);
            idleThreads.remove(lastIndex);
            thread.setTarget(target);
        }else {
            //没有空闲线程新增一个线程并使用
            threadCounter ++ ;
            thread = new PThread(target, "PThread #" + threadCounter, this);
            thread.start();
        }
    }
}
