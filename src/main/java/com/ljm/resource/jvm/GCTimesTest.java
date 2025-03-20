package com.ljm.resource.jvm;

import java.util.Vector;

/**
 * Created by jiamin5 on 2023/3/3.
 */
public class GCTimesTest {
    public static void main(String[] args) throws InterruptedException {
        Vector vector = new Vector();
        for (int i = 0; i < 20; i++) {
            byte[] bytes = new byte[1024*1024];
            vector.add(bytes);
            Thread.sleep(5000);
            if(vector.size() > 3){
                vector.clear();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

