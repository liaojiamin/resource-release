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
        String fileName = "E:\\learn\\�������\\MYSQL.md";
        long fileLength = new File(fileName).length();
        int bufferCount = 1+ (int) (fileLength / BUFFER_SIZE);
        MappedByteBuffer[] byteBuffers = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;

        String fileName_1 = "E:\\learn\\�������\\MYSQL_1.md";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName_1);
        FileChannel writeChannel = fileOutputStream.getChannel();

        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file = new RandomAccessFile(fileName, "r");
                Integer size = (int)Math.min(remaining, BUFFER_SIZE);
                /**
                 * ���� 1:FileChannel.MapMode.READ_ONLY ʹ��ֻ��ģʽ
                 * ���� 2��i * BUFFER_SIZE�����ζ�ȡ�ļ��Ŀ�ʼλ��
                 * ���� 3:size: ��ӳ�䵽�ڴ�Ĵ�С����������λ�ã������� MYSQL.md �Ķ��ٸ��ֽ�ӳ�䵽�ڴ棬�˴�����ÿ�ζ���ȡ�̶�����С1024���������1024ʱ�򣬶�ȡʣ��� remaining
                 * ����ֱ���޸ĵķ�Χ���� 0-fileLength
                 * ʵ������ DirectByteBuffer
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
