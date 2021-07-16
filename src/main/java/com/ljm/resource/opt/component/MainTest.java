package com.ljm.resource.opt.component;

import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 14:03 2020/3/24
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
//        IPacketCreator creator = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new ConcreteComponent()));
//        System.out.println(creator.handleContent());
        testIOStream();
    }

    public static void testIOStream() throws IOException {
//        DataOutputStream dataOutputStream =
//                new DataOutputStream(
//                        new BufferedOutputStream(
//                                new FileOutputStream("C:\\Users\\Administrator\\Desktop\\重要信息.txt")));
//        没有缓冲功能的流对象
        DataOutputStream dataOutputStream =
                new DataOutputStream(
                                new FileOutputStream("C:\\Users\\Administrator\\Desktop\\重要信息.txt"));
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dataOutputStream.write(i);
        }
        dataOutputStream.close();
        System.out.println("speed: " + (System.currentTimeMillis() - begin));
    }
}
