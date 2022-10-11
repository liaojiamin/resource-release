package com.ljm.resource.nio;

import io.netty.buffer.ByteBuf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 14:09 2022/8/1
 */
public class FileChannelMapCopyFile {

    public static void main(String[] args) throws FileNotFoundException {
        int BUFFER_SIZE = 1024;
        String fileName = "E:\\learn\\问题汇总\\MYSQL.md";
        long fileLength = new File(fileName).length();
        int bufferCount = 1+ (int) (fileLength / BUFFER_SIZE);
        MappedByteBuffer[] byteBuffers = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;

        String fileName_1 = "E:\\learn\\问题汇总\\MYSQL_1.md";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName_1);
        FileChannel writeChannel = fileOutputStream.getChannel();

        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file = new RandomAccessFile(fileName, "r");
                Integer size = (int)Math.min(remaining, BUFFER_SIZE);
                /**
                 * 参数 1:FileChannel.MapMode.READ_ONLY 使用只读模式
                 * 参数 2：i * BUFFER_SIZE：本次读取文件的开始位置
                 * 参数 3:size: 是映射到内存的大小（不是索引位置），即将 MYSQL.md 的多少个字节映射到内存，此处我们每次都读取固定个大小1024，当最后不足1024时候，读取剩余的 remaining
                 * 可以直接修改的范围就是 0-fileLength
                 * 实际类型 DirectByteBuffer
                 */
                byteBuffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY, i * BUFFER_SIZE, size);
                ByteBuffer byteBuffers1 = byteBuffers[i].get(new byte[size]);
                byteBuffers1.flip();
                writeChannel.write(byteBuffers1);
            }catch (Exception e){
                e.printStackTrace();
            }
            remaining -= BUFFER_SIZE;
        }
    }
}
