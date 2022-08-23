package com.ljm.resource.netty.chat.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liaojiamin
 * @Date:Created in 11:21 2021/11/3
 */
public class MsgCounter {
    private static AtomicInteger counter = new AtomicInteger();
    private static Long startTime;
    private static final Object Lock = new Object();
    private static Map<Integer, Long> countTimeMapping = new LinkedHashMap<>(3);


    public static void start(){
        if(MsgCounter.startTime == null){
            synchronized (Lock){
                if(MsgCounter.startTime == null){
                    MsgCounter.startTime = System.currentTimeMillis();
                }
            }
        }
    }

    public static void count(){
        start();
        int count = counter.incrementAndGet();
        int finishCount = count/6;
        boolean number_validate = finishCount == MsgConstant.COUNT_LEVEL_1
                || finishCount == MsgConstant.COUNT_LEVEL_2
                || finishCount == MsgConstant.COUNT_LEVEL_3;
        boolean ask_ans_validate = count % 6 == 0;
        if(number_validate && ask_ans_validate){
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("遍历" + finishCount + "次，花费:" + time + "ms");
            countTimeMapping.put(finishCount, time);
            System.out.println(countTimeMapping);
        }
    }

}
