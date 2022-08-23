package com.ljm.resource.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 16:06 2022/8/16
 */
public class ChannelInPutDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    public static void main(String[] args) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        int flap = fileChannel.read(byteBuffer);
        while (flap > 0){
            System.out.println(new String(byteBuffer.array()));
            flap = fileChannel.read(byteBuffer);
        }
    }
}
