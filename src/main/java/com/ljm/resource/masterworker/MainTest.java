package com.ljm.resource.masterworker;

import java.util.Map;
import java.util.Set;

/**
 * @author liaojiamin
 * @Date:Created in 13:52 2020/4/14
 */
public class MainTest {
    public static void main(String[] args) {
        Master m = new Master(new PlusWorker(), 5);
        for (int i = 0; i < 100; i++) {
            m.submit(i);
        }
        m.execute();
        int re = 0;
        Map<String, Object> resultMap = m.getResultMap();
        while (resultMap.size() > 0 || !m.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String s : keys) {
                key = s;
                break;
            }
            Integer i = null;
            if(key != null){
                i = (Integer)resultMap.get(key);
            }
            if(i!= null){
                re+=i;
            }
            if(key != null){
                resultMap.remove(key);
            }
        }
        System.out.println(re);
    }
}
