package com.ljm.resource.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author liaojiamin
 * @Date:Created in 11:38 2020/6/9
 */
public class SocketDemo {
    public static void main(String[] args) {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            System.out.println("i can connect to time.nist.gov");
            socket.setSoTimeout(10000);
            InputStream inputStream = socket.getInputStream();
            StringBuffer time = new StringBuffer();
            InputStreamReader reader = new InputStreamReader(inputStream, "ASCII");
            for (int i = reader.read(); i != -1; i= reader.read()) {
                time.append((char) i);
            }
            System.out.println(time);
        }catch (Exception e){
            System.out.println("Could not connect to time.nist.gov");
        }
    }
}
