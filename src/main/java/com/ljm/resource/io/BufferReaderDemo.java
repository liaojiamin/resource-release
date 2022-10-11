package com.ljm.resource.io;

import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 10:36 2022/8/5
 */
public class BufferReaderDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\MYSQL.md";
    private static final String resultFilePath = "E:\\learn\\问题汇总\\MYSQL_1.md";
    public static void bufferReadFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        FileOutputStream fileOutputStream = new FileOutputStream(resultFilePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        String readLineStr;
        while ((readLineStr = bufferedReader.readLine()) != null){
            System.out.print(readLineStr);
            bufferedWriter.write(readLineStr + "\n");
        }
        fileInputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
        fileOutputStream.close();
        outputStreamWriter.close();
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        bufferReadFile();
    }
}
