package com.ljm.resource.io;

import java.io.*;
import java.net.Socket;

/**
 * @author liaojiamin
 * @Date:Created in 11:04 2022/8/16
 */
public class BIOClientSocket {
    private static final String filePath = "E:\\learn\\ÎÊÌâ»ã×Ü\\MYSQL.md";
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        Socket socket = new Socket("127.0.0.1", 6666);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        while (bufferedInputStream.read(bytes) >= 0){
            bufferedOutputStream.write(bytes);
        }
        fileInputStream.close();
        bufferedInputStream.close();
        socket.close();
        bufferedInputStream.close();
    }
}
