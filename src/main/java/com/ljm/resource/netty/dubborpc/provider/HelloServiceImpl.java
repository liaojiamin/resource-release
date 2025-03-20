package com.ljm.resource.netty.dubborpc.provider;

import com.ljm.resource.netty.dubborpc.publicinterface.HelloService;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class HelloServiceImpl implements HelloService {
    private static int count = 0;
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端的消息 = "+ mes);
        if(mes != null){
            return " 你好客户端，我已经收到你的消息 [" + mes + "] 第"+ (++count)+ "次";
        }else {
            return "你好客户端，我已经收到你的消息";
        }
    }
}
