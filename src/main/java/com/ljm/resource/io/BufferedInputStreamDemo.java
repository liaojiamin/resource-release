package com.ljm.resource.io;

import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 10:23 2022/8/5
 */
public class BufferedInputStreamDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String resultFilePath = "E:\\learn\\问题汇总\\MYSQL_1.md";
    public static void bufferInputStreamFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(resultFilePath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] readByte = new byte[1024];
        while (bufferedInputStream.read(readByte) >= 0){
            bufferedOutputStream.write(readByte);
        }
        fileInputStream.close();
        bufferedInputStream.close();
        fileOutputStream.close();
        bufferedOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        bufferInputStreamFile();
    }
}
