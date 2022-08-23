package com.ljm.resource.io;

import java.io.*;
import java.util.Arrays;

import static com.ljm.resource.io.BufferReaderDemo.bufferReadFile;

/**
 * @author liaojiamin
 * @Date:Created in 14:04 2022/8/5
 */
public class ByteArrayInputStreamDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String resultFilePath = "E:\\learn\\问题汇总\\MYSQL_1.md";

    public static void byteArrayInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] tempByte = new byte[1024 * 10];
        while (fileInputStream.read(tempByte) != -1) {
            byteArrayOutputStream.write(tempByte);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(resultFilePath);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        while (byteArrayInputStream.read(tempByte) != -1) {
            fileOutputStream.write(tempByte);
        }
        fileInputStream.close();
        byteArrayOutputStream.close();
        fileOutputStream.close();
        byteArrayInputStream.close();
    }

    public static void main(String[] args) throws IOException {
        byteArrayInputStream();
    }
}
