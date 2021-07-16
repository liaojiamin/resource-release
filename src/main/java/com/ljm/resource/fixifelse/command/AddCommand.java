package com.ljm.resource.fixifelse.command;

/**
 * @author liaojiamin
 * @Date:Created in 11:38 2020/6/13
 */
public class AddCommand implements Command {
    private int a;
    private int b;

    public AddCommand(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer execute() {
        return a + b;
    }
}
