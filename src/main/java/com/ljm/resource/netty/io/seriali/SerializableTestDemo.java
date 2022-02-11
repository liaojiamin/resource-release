package com.ljm.resource.netty.io.seriali;

import java.io.*;

/**
 * @author liaojiamin
 * @Date:Created in 10:04 2022/2/8
 */
public class SerializableTestDemo {
    public static void main(String[] args) {
        SerializableClass serializableClass = new SerializableClass();
        serializableClass.setAge(12);
        serializableClass.setName("张三");
        System.out.println(serializableClass);
        //写文件
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file"));
//            oos.writeObject(serializableClass);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        //读文件
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(("file"))));
            SerializableClass serializableClass1 = (SerializableClass) objectInputStream.readObject();
            System.out.println(serializableClass1.getAge());
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
