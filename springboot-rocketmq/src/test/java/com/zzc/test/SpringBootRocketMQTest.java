package com.zzc.test;

import com.zzc.test.core.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengzechao
 * @date 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRocketMQTest {
    @Autowired
    UserProducer userProducer;


    /**
     *
     * Test不启动Tomcat
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
//        userProducer.sendMessage();
        System.out.println(userProducer.getClass().getName());
        Thread.sleep(1000000);
    }


}
