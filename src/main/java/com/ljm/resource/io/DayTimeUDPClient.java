package com.ljm.resource.io;

import java.io.IOException;
import java.net.*;

/**
 * @author liaojiamin
 * @Date:Created in 10:59 2020/6/9
 */
public class DayTimeUDPClient {
    private static final Integer PORT = 13;
    private static final String HOSTNAME = "time.nist.gov";
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(0);
        datagramSocket.setSoTimeout(10000);
        InetAddress host = InetAddress.getByName(HOSTNAME);
        DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);
        DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.send(request);
        datagramSocket.receive(response);
        String result = new String(response.getData(), 0, response.getLength(), "US-ASCII");
        System.out.println(result);
    }
}
