package com.zzc.test.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzc.test.service.HelloService;

/**
 * @author zhengzechao
 * @date 2018/4/15
 * Email ooczzoo@gmail.com
 */


@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        interfaceClass = HelloService.class

)

public class HelloServiceImpl implements HelloService{
    @Override
    public String say(String name) {

        return "hello "+name;
    }
}
