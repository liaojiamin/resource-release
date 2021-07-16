package com.ljm.resource.opt.component;

/**
 * @author liaojiamin
 * @Date:Created in 14:01 2020/3/24
 */
public class PacketHTTPHeaderCreator extends PacketDecorator {
    public PacketHTTPHeaderCreator(IPacketCreator c) {
        super(c);
    }

    @Override
    public String handleContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cache-Control:no-cache\n");
        sb.append(component.handleContent());
        return sb.toString();
    }
}
