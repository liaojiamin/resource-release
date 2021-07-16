package com.ljm.resource.nio;

import javax.sound.midi.Soundbank;
import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 17:06 2020/4/10
 */
public class IODemo {
    public static void main(String[] args) throws Exception {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\test.txt")));
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\test.txt"));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dos.writeBytes(String.valueOf(i) + "\r\n");
        }
        dos.close();
        System.out.println("speed time: "+ (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\test.txt")));
//        DataInputStream dis = new DataInputStream(new FileInputStream("D:\\test.txt"));
        while (dis.readLine() != null){
//            System.out.println(dis.readLine());
        }
        dis.close();
        System.out.println("speed time: "+ (System.currentTimeMillis() - start));
    }
}
