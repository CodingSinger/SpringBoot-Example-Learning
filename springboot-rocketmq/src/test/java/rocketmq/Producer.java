package rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import rocketmq.schedule_example.HelloHook;

import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/4/13
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        int i1 = 0;
        for (i1 = 0; i1 < 3; i1++) {
            System.out.println("ge");

        }
        System.out.println(i1);
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        HelloHook helloHook = new HelloHook();
        producer.getDefaultMQProducerImpl().registerSendMessageHook(helloHook);
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        for (int i = 0; i < 100; i++){
            Message msg = new Message("TopicTest",
                    "TagD",
                    "OrderID188",
                    ("Hello world"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//             控制发送的队列
//            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                    System.out.println(mqs.size());
//                    return mqs.get(0);
//                }
//            },1);
            SendResult sendResult = producer.send(msg);

//            Message msg1 = new Message("TopicTest2",
//                    "TagD",
//                    "OrderID188",
//                    ("Hello world2"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
////             控制发送的队列
//            SendResult sendResult1 = producer.send(msg1, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                    System.out.println(mqs.size());
//                    return mqs.get(0);
//                }
//            },1);
//            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        System.out.println(producer.getHeartbeatBrokerInterval());
        producer.shutdown();
    }
}
