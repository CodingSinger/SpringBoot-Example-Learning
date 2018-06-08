package com.zzc.test.core;

import com.zzc.test.model.User;
import io.terminus.common.rocketmq.core.TerminusMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/4/14
 */

@Component
public class UserProducer{
    @Autowired
    private TerminusMQProducer producer; // 注入 producer

    public void sendMessage(String topic,String tag,User user) {

        SendResult result = producer.send(topic,tag, user);
        System.out.println(result);
    }

}