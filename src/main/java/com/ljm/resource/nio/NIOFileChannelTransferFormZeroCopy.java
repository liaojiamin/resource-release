package com.ljm.resource.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 18:36 2022/8/16
 */
public class NIOFileChannelTransferFormZeroCopy {
    private static final String filePath = "E:\\learn\\�������\\MYSQL.md";
    private static final String filePathResult = "E:\\learn\\�������\\MYSQL_1.md";
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePathResult);
        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outputFileChannel = fileOutputStream.getChannel();
        //��Ŀ��ͨ���и������ݵ���ǰͨ��
//        outputFileChannel.transferFrom(inputFileChannel, 0, inputFileChannel.size());
        //�����ݴӵ�ǰͨ�����Ƹ�Ŀ��ͨ��
        inputFileChannel.transferTo(0, inputFileChannel.size(), outputFileChannel);
        inputFileChannel.close();
        outputFileChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
