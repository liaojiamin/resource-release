package com.ljm.resource.netty.chat.protocal.packet;

/**
 * @author liaojiamin
 * @Date:Created in 17:59 2021/11/2
 */
public abstract class Packet {
    private Byte version = 1;
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
