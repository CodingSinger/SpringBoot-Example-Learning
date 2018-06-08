package com.zzc.test.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzc.test.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengzechao
 * @date 2018/4/15
 * Email ooczzoo@gmail.com
 */
@RestController
public class TestController {



    @Reference(version = "1.0.0",

            url = "dubbo://localhost:12345")
    HelloService service;
    @GetMapping(value = "/demo")
    public String say(@RequestParam String name){


        return service.say(name);

    }
}
