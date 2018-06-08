package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

import static org.apache.rocketmq.common.protocol.heartbeat.MessageModel.BROADCASTING;

/**
 * @author zhengzechao
 * @date 2018/4/13
 */
public class Consumer {
    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
                consumer.setNamesrvAddr("localhost:9876");
                //set to broadcast mode
//        consumer.setMessageModel(BROADCASTING);
                consumer.setConsumerGroup("test2");
                try {
                    consumer.subscribe("TopicTest2", "TagA || TagC || TagD");
                    consumer.subscribe("TopicTest", "TagA || TagC || TagD"); //后一个订阅的会覆盖之前的
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
//                consumer.registerMessageListener(new MessageListenerOrderly() {
//
//                    @Override
//                    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                        msgs.forEach((msg) -> {
////                    System.out.println(msgs.size());
//                            System.out.println("queueId: " + context.getMessageQueue().getQueueId()); //获取当前接受的Id
//                            System.out.println(new String(msg.getBody()) + " -" + Thread.currentThread().getName());
//                        });
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                        return ConsumeOrderlyStatus.SUCCESS;
//                    }
//
//
//                });

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    synchronized (this){
                        System.out.println("queueId: " + context.getMessageQueue().getQueueId()); //获取当前接受的Id
                    System.out.println(new String(msg.getBody()) + " -" + Thread.currentThread().getName());
                    }
                });


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
                try {
                    consumer.start();
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
                System.out.printf("Broadcast Consumer Started.%n");
            }
        });


        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("localhost:9876");
        //set to broadcast mode
        consumer.setConsumerGroup("test3");
//        consumer.subscribe("TopicTest2", "TagA || TagC || TagD");
        consumer.subscribe("TopicTest", "TagA || TagC || TagD"); //后一个订阅的会覆盖之前的


//        consumer.registerMessageListener(new MessageListenerOrderly() {
//
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                msgs.forEach((msg) -> {
////                    System.out.println(msgs.size());
//                    System.out.println("queueId: " + context.getMessageQueue().getQueueId()); //获取当前接受的Id
//                    System.out.println(new String(msg.getBody()) + " -" + Thread.currentThread().getName());
//                });
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//
//
//        });

        consumer.setConsumeThreadMax(1);
        consumer.setConsumeThreadMin(1);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    synchronized (this){
                        System.out.println("queueId: " + context.getMessageQueue().getQueueId()); //获取当前接受的Id
                    System.out.println(new String(msg.getBody()) + " -" + Thread.currentThread().getName());
                    }
                });


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Broadcast Consumer Started.%n");
    }
}
