package com.ljm.resource.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 16:57 2022/7/28
 */
public class Serialize implements Serializable {
    private static final long serialVersionUID = 8712637861L;
    public int num = 1099;

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("D:/serialize.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
