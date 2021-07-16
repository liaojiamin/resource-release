package com.ljm.resource.thread;

import java.util.concurrent.Callable;

/**
 * @author liaojiamin
 * @Date:Created in 17:18 2020/4/13
 */
public class NewRealData implements Callable<String> {
    private String para;
    public NewRealData(String para){
        this.para = para;
    }


    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            Thread.sleep(100);
        }
        return sb.toString();
    }
}
