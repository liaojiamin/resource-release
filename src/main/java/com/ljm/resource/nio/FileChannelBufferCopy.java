package com.ljm.resource.nio;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 18:14 2022/8/16
 */
public class FileChannelBufferCopy {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String filePathResult = "E:\\learn\\问题汇总\\MYSQL_1.md";
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePathResult);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        FileChannel fileChannel = fileInputStream.getChannel();
        while (fileChannel.read(byteBuffer) > 0){
            byteBuffer.flip();
            fileOutputStream.getChannel().write(byteBuffer);
            byteBuffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();

    }
}
