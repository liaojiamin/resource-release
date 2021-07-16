package com.ljm.resource.opt.component;

/**
 * @author liaojiamin
 * @Date:Created in 13:56 2020/3/24
 */
public class ConcreteComponent implements IPacketCreator {
    @Override
    public String handleContent() {
        return "Content of packet";
    }
}
