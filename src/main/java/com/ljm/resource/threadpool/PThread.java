package com.ljm.resource.threadpool;



/**
 * @author liaojiamin
 * @Date:Created in 10:28 2020/4/15
 */
public class PThread extends Thread {
    //线程池
    private ThreadPool threadPool;
    //任务
    private Runnable target;
    private boolean isShutDown = false;
    private boolean isIdle = false;

    public PThread(Runnable target,String name, ThreadPool pool){
        super(name);
        this.threadPool = pool;
        this.target = target;
    }

    public Runnable getTarget(){
        return target;
    }

    public boolean isIdle(){
        return isIdle;
    }

    @Override
    public void run(){
        //只有没有关闭，一直运行不结束线程
        while (!isShutDown){
            isIdle = false;
            if(target != null){
                //运行任务
                target.run();
            }
            //结束，修改闲置状态
            isIdle = true;
            threadPool.repool(this);
            synchronized (this){
                try {
                    //等待新任务
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isIdle = false;
        }
    }

    public synchronized void setTarget(Runnable newTarget){
        target = newTarget;
        //设置任务后，通知run方法，开始执行任务
        notifyAll();
    }

    public synchronized void shutDown(){
        isShutDown = true;
        notifyAll();
    }
}
