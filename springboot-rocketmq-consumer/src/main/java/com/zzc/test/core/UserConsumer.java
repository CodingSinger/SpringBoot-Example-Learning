package com.zzc.test.core;

import com.zzc.test.model.User;
import io.terminus.common.rocketmq.annotation.ConsumeMode;
import io.terminus.common.rocketmq.annotation.MQConsumer;
import io.terminus.common.rocketmq.annotation.MQSubscribe;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/4/14
 */
@MQConsumer
@Component
public class UserConsumer {
    @Autowired
    UserProducer producer;
    @MQSubscribe(topic = "testTopic",consumerGroup = "group11",consumeMode = ConsumeMode.ORDERLY,messageMode = MessageModel.BROADCASTING)
    public void test(User user) {
        System.out.println(producer);
        System.out.println("subscribe: " + user.getName());
    }

    @MQSubscribe(topic = "testTopic",consumerGroup = "group10",consumeMode= ConsumeMode.ORDERLY,messageMode = MessageModel.BROADCASTING)
    public void test1(User user){
        System.out.println(producer);
        System.out.println("subscribe: " + user.getName());
    }
}
