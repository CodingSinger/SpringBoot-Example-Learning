package com.zzc.test.springboothelloworld;

import com.zzc.test.springboothelloworld.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootHelloworldApplicationTests {

	@Autowired
	private Person person;

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

}
