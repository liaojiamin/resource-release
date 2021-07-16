package com.ljm.resource.rx.leetcode;

import java.math.BigDecimal;

public class SwitchTest {
    public static void main(String[] args) {
        BigDecimal bigDecimala = new BigDecimal(0.1);
        System.out.println(bigDecimala);
        BigDecimal bigDecimalb = new BigDecimal("0.1");
        System.out.println(bigDecimalb);
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }
}
