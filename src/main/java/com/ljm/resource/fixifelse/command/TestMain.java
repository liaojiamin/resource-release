package com.ljm.resource.fixifelse.command;

/**
 * @author liaojiamin
 * @Date:Created in 11:41 2020/6/13
 */
public class TestMain {
    public static void main(String[] args) {
        Calculator calculation = new Calculator();
        System.out.println(calculation.calculator(new AddCommand(100, 200)));
    }
}
