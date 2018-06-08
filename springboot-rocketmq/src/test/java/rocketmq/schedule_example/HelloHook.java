package rocketmq.schedule_example;

import org.apache.rocketmq.client.hook.SendMessageContext;
import org.apache.rocketmq.client.hook.SendMessageHook;

/**
 * @author zhengzechao
 * @date 2018/5/6
 * Email ooczzoo@gmail.com
 */
public class HelloHook implements SendMessageHook {

    @Override
    public String hookName() {
        return "helloHoook";
    }

    @Override
    public void sendMessageBefore(SendMessageContext context) {

        System.out.println("before");
    }

    @Override
    public void sendMessageAfter(SendMessageContext context) {
        System.out.println("after");

    }
}
