package rocketmq.schedule_example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;


/**
 * @author zhengzechao
 * @date 2018/4/13
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {
//        Thread t = new Thread(new Consumer());
//        t.start();
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        // Launch producer
        producer.start();
        int totalMessagesToSend = 1000;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(3);
            // Send the message
            producer.send(message);
        }

        // Shutdown producer after use.
        producer.shutdown();
    }



    public static class Consumer implements Runnable{


        @Override
        public void run() {
            // Instantiate message consumer
            try {
                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
                // Subscribe topics
                consumer.subscribe("TestTopic", "*");
                // Register message listener
                consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
                            for (MessageExt message : messages) {
                                // Print approximate delay time period
    //                        System.out.println("Receive message[msg=" + new String(message.getBody())+ "] "+
    //                                "[Thread Name:"+Thread.currentThread().getName()+" ] "
    //                                + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                                System.out.println(new String(message.getBody()));
                            }

                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                );

                // Launch consumer
                consumer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
    }
}
/**

    延时消息
 这种消息一般适用于消息生产和消费之间有时间窗口要求的场景。比如说我们网购时，下单之后是有一个支付时间，
 超过这个时间未支付，系统就应该自动关闭该笔订单。那么在订单创建的时候就会就需要发送一条延时消息（延时15分钟）
 后投递给 consumer，consumer 接收消息后再对订单的支付状态进行判断是否关闭订单。

 */