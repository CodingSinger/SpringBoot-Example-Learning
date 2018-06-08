package com.zzc.test.controller;

import com.zzc.test.core.UserProducer;
import com.zzc.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengzechao
 * @date 2018/4/14
 */


@RestController

public class TestController {

    @Autowired
    private UserProducer producer;


    @GetMapping(value = "/demo")
    public void send(){
        System.out.println(producer);
        User user = new User();
        user.setName("马大哈");
        producer.sendMessage("testTopic","",user);

    }



    @GetMapping(value = "/demo2")
    public void send2(){

    }
}
