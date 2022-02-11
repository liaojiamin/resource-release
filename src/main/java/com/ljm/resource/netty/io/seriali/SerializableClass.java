package com.ljm.resource.netty.io.seriali;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 10:04 2022/2/8
 */
public class SerializableClass implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
