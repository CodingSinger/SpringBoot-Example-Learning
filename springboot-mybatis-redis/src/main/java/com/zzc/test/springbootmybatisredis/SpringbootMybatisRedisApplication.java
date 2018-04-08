package com.zzc.test.springbootmybatisredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzc.test.springbootmybatisredis.Dao")
public class SpringbootMybatisRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRedisApplication.class, args);
	}
}
