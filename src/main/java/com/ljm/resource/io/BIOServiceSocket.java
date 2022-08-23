package com.ljm.resource.io;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author liaojiamin
 * @Date:Created in 11:03 2022/8/16
 */
public class BIOServiceSocket {
    private static final String resultFilePath = "E:\\learn\\问题汇总\\MYSQL_1.md";

    public static void main(String[] args) throws IOException {
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(4, new BasicThreadFactory.Builder().build());
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is start");
        while (true) {
            System.out.println("listener thread id = " + Thread.currentThread().getName() + " name = " + Thread.currentThread().getName());
            System.out.println("wait connection....");
            Socket socket = serverSocket.accept();
            System.out.println("connection one client");
            scheduledExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            System.out.println("listener thread id = " + Thread.currentThread().getName() + " name = " + Thread.currentThread().getName());
            FileOutputStream fileOutputStream = new FileOutputStream(resultFilePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            while (bufferedInputStream.read(bytes) >= 0) {
                System.out.println("listener thread id = " + Thread.currentThread().getName() + " name = " + Thread.currentThread().getName());
                System.out.println("read socket ...");
               bufferedOutputStream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("close client connection");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
