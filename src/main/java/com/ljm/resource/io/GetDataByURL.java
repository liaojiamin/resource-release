package com.ljm.resource.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author liaojiamin
 * @Date:Created in 11:13 2020/6/16
 */
public class GetDataByURL {
    public static void main(String[] args) throws IOException {
        Long sendMix = Math.abs(System.currentTimeMillis() - 1592150400000L);
        System.out.println("sendMin: "+ sendMix);
        Long time = sendMix/(1000*60);
        System.out.println("time: "+time);
        double times = 101 + 119 + (time/2);
        System.out.println(times);
    }
}
