package com.ljm.resource.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ���ȡ��ʽ�ܽ�
 *
 * @author liaojiamin
 * @Date:Created in 16:16 2021/7/22
 */
public class FileReadDemo {
    public static void main(String[] args) {
        List<String> fileArray = readFileToArray("E:\\learn\\�������\\MYSQL.md");
        fileArray.forEach(System.out::println);
        List<String> fileArray2 = inputStreamReaderToArray("E:\\learn\\�������\\MYSQL.md");
        fileArray2.forEach(System.out::println);
    }



    /**
     * FileInputStream ->  InputStreamReader -> BufferedReader ��ȡ�ļ�
     * */
    public static List<String> inputStreamReaderToArray(String filePath) {
        if (filePath == null) {
            return null;
        }
        List<String> resultList = new ArrayList<>();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null){
                resultList.add(str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(inputStreamReader != null){
                    fileInputStream.close();
                }
                if(bufferedReader !=  null){
                    bufferedReader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }
    /**
     * FileReader -> BufferReader ��ȡ�ļ�
     * */
    public static List<String> readFileToArray(String filePath) {
        if (filePath == null) {
            return null;
        }
        List<String> resultList = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                resultList.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
