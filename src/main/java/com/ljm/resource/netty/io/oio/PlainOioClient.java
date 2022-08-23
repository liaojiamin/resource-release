package com.ljm.resource.netty.io.oio;

import java.io.*;
import java.net.Socket;

/**
 * @author liaojiamin
 * @Date:Created in 16:39 2021/7/27
 */
public class PlainOioClient {


    private static String info = "hello server";
    private static byte[] bytes;
    private static String serverResult;

    public static void main(String[] args) throws IOException {
        PlainOioClient plainOioClient = new PlainOioClient();
        plainOioClient.client("127.0.0.1", 3333);
    }

    public void client(String host, int port) throws IOException{
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        bytes = info.getBytes();
        ps.write(bytes);
        ps.flush();
        //必须先关闭输出流，服务端才能收到结果
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ( (serverResult = bufferedReader.readLine()) != null){
            System.out.println("serverResult: "+ serverResult);
        }
        socket.shutdownInput();
        bufferedReader.close();
        ps.close();
        outputStream.close();
        socket.close();
    }
}
