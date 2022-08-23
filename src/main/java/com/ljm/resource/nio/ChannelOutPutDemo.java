package com.ljm.resource.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 16:02 2022/8/16
 */
public class ChannelOutPutDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL_1.md";
    public static void main(String[] args) throws IOException {
        String testContent = "测试写入数据到文件";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(testContent.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
