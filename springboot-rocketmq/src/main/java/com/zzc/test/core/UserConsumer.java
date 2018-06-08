package com.zzc.test.core;

import com.zzc.test.model.User;
import io.terminus.common.rocketmq.annotation.ConsumeMode;
import io.terminus.common.rocketmq.annotation.MQConsumer;
import io.terminus.common.rocketmq.annotation.MQSubscribe;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/4/14
 */
//@MQConsumer
//@Component
//public class UserConsumer {
//    @MQSubscribe(topic = "testTopic",consumerGroup = "group11",consumeMode = ConsumeMode.ORDERLY)
//    public void test(User user) {
//        System.out.println("subscribe: " + user.getName());
//    }
//}


/**
    因为消息队列主要用在分布式集群中，所以一般都不会在一个模块中同时用生产者和消费者

 **/