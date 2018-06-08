package com.zzc.test.springboothelloworld;

import com.zzc.test.springboothelloworld.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhengzechao
 */
@SpringBootApplication
public class SpringbootHelloworldApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(SpringbootHelloworldApplication.class, args);
		System.out.println(context.getBean(Person.class));

	}
}
