package com.ljm.resource.io;

import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 15:14 2022/8/5
 */
public class CharArrayReaderDemo {

    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String resultFilePath = "E:\\learn\\问题汇总\\MYSQL_1.md";

    public static void bufferInputStreamFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        char[] readChar = new char[1024 * 10];
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        while (inputStreamReader.read(readChar) != -1) {
            charArrayWriter.write(readChar);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(resultFilePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        CharArrayReader charArrayReader = new CharArrayReader(charArrayWriter.toCharArray());
        while (charArrayReader.read(readChar) != -1){
            outputStreamWriter.write(readChar);
        }
        fileInputStream.close();
        inputStreamReader.close();
        charArrayWriter.close();
        fileOutputStream.close();
        charArrayReader.close();
    }

    public static void main(String[] args) throws IOException {
        bufferInputStreamFile();
    }
}
