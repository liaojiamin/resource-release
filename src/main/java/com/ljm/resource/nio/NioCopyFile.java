package com.ljm.resource.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaojiamin
 * @Date:Created in 18:15 2020/4/2
 */
public class NioCopyFile {
    public static void nioCopyFileTest(String source, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            byteBuffer.clear();
            int len = readChannel.read(byteBuffer);
            if(len == -1){
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        }
        readChannel.close();
        writeChannel.close();
    }

}
