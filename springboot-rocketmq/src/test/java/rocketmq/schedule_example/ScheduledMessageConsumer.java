package rocketmq.schedule_example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengzechao
 * @date 2018/4/13
 */
public class ScheduledMessageConsumer {

    static AtomicInteger count = new AtomicInteger(0);








    public static void main(String[] args) throws Exception {


        // Instantiate message consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
        // Subscribe topics
        consumer.subscribe("TestTopic", "*");
        // Register message listener
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
                    for (MessageExt message : messages) {
                        // Print approximate delay time period
                        count.getAndIncrement();
//                        System.out.println("Receive message[msg=" + new String(message.getBody())+ "] "+
//                                "[Thread Name:"+Thread.currentThread().getName()+" ] "
//                                + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                        System.out.println(new String(message.getBody()));
                    }

                    System.out.println("count"+count.get());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
        );

        // Launch consumer
        consumer.start();
    }
}
