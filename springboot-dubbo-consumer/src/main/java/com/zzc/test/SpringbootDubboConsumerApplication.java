package com.zzc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * 目前官方依赖好像不能兼容springboot2.0版本的
 * 所以本例中采用SpringBoot1.5.1
 */
@SpringBootApplication
public class SpringbootDubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboConsumerApplication.class, args);
	}
}
