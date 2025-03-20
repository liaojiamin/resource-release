package com.ljm.resource.netty.dubborpc.customer;

import com.ljm.resource.netty.dubborpc.netty.NettyClient;
import com.ljm.resource.netty.dubborpc.publicinterface.HelloService;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class ClientBootStrap {
    //定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        NettyClient customer = new NettyClient();
        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);
        for(;;){
            Thread.sleep(2*1000);
            //通过代理对象调用服务提供者的方法(服务)
            String res = service.hello("你好 my dubbo~~~");
            System.out.println("调用结果 res= "+ res);
        }
    }
}
