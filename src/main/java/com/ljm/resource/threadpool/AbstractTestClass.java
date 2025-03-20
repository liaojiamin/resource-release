package com.ljm.resource.threadpool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamin5 on 2023/4/7.
 */
public abstract class AbstractTestClass {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(12);
        Map<String, String> map1 = new HashMap<>(13);
        Map<String, String> map2 = new HashMap<>(14);
        Map<String, String> map3 =  new HashMap<>(15);
        Map<String, String> map4 = new HashMap<>(17);
        System.out.println(map.size());
        for (int i = 0; i <= 10; i++) {
            if(i>=3){
                break;
            }
            for (int i1 = 0; i1 <= 10; i1++) {
                if(i1 >=3){
                    continue;
                }
                System.out.print(i1+i+" ");
            }
        }
    }
}
