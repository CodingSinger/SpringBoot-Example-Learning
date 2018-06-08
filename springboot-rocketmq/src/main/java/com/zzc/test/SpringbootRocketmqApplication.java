package com.zzc.test;

import com.zzc.test.core.UserProducer;
import com.zzc.test.model.User;
import io.terminus.common.rocketmq.annotation.ConsumeMode;
import io.terminus.common.rocketmq.annotation.MQConsumer;
import io.terminus.common.rocketmq.annotation.MQSubscribe;
import io.terminus.common.rocketmq.core.TerminusMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringbootRocketmqApplication {





	public static void main(String[] args) {

		SpringApplication.run(SpringbootRocketmqApplication.class, args);



	}








}
