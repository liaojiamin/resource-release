package com.ljm.resource.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by jiamin5 on 2023/4/7.
 */
public class ParallelStreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i+"");
        }

        Vector<String> vector = new Vector<>();
        list.parallelStream().forEach(str -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vector.add(str+"test1");
            System.out.println("拼出");
        });
        //全部任务为阻塞执行，之后才执行到这一行
        System.out.println(vector.size());
        vector.forEach(str->{
            System.out.println("获取结果"+str);
        });

    }
}
