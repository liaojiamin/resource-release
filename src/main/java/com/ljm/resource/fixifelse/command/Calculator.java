package com.ljm.resource.fixifelse.command;

/**
 * @author liaojiamin
 * @Date:Created in 11:40 2020/6/13
 */
public class Calculator {
    public int calculator(Command command){
        return command.execute();
    }
}
