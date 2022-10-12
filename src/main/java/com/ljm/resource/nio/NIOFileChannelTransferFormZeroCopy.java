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
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String filePathResult = "E:\\learn\\问题汇总\\MYSQL_1.md";
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePathResult);
        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outputFileChannel = fileOutputStream.getChannel();
        //从目标通道中复制数据到当前通道
//        outputFileChannel.transferFrom(inputFileChannel, 0, inputFileChannel.size());
        //把数据从当前通道复制给目标通道
        inputFileChannel.transferTo(0, inputFileChannel.size(), outputFileChannel);
        inputFileChannel.close();
        outputFileChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
