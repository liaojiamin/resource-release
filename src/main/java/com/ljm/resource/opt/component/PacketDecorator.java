package com.ljm.resource.opt.component;

/**
 * @author liaojiamin
 * @Date:Created in 13:57 2020/3/24
 */
public abstract class PacketDecorator implements IPacketCreator {
    IPacketCreator component;
    public PacketDecorator(IPacketCreator c){
        component = c;
    }
}
