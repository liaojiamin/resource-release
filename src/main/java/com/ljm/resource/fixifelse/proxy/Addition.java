package com.ljm.resource.fixifelse.proxy;

/**
 * @author liaojiamin
 * @Date:Created in 11:05 2020/6/13
 */
public class Addition implements Operator {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }

    @Override
    public String getType() {
        return "add";
    }
}
