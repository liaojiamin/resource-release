package com.ljm.resource.fixifelse.proxy;

/**
 * @author liaojiamin
 * @Date:Created in 11:17 2020/6/13
 */
public class TestMain {
    public static void main(String[] args) {
        int a = 100, b = 200;

        OperationFaction operationFaction = new OperationFaction();
        System.out.println(operationFaction.calculateUsingFactory(a, b, "add"));
    }

}
