package com.ljm.resource.netty.io.oio;

import io.netty.util.CharsetUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liaojiamin
 * @Date:Created in 16:28 2021/7/27
 */
public class PlainOioServer {
    public static void main(String[] args) throws IOException {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.serve(3333);
    }
    private static String clientResult;
    /**
     * JDK api 实现oio
     * */
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;){
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from  "+ clientSocket);
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                InputStream in = null;
                                OutputStream out = null;
                                System.out.println(Thread.currentThread().getName() + " start");
                                try {
                                    in = clientSocket.getInputStream();
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                                    while ((clientResult = bufferedReader.readLine()) != null){
                                        System.out.println(clientResult);
                                        break;
                                    }
                                    clientSocket.shutdownInput();
                                    out = clientSocket.getOutputStream();
                                    out.write("Hi!\r\n".getBytes(CharsetUtil.UTF_8));
                                    out.write(clientResult.getBytes());
                                    out.flush();
                                    clientSocket.shutdownOutput();

                                    in.close();
                                    out.close();
                                    clientSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        in.close();
                                        out.close();
                                        clientSocket.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                ).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
