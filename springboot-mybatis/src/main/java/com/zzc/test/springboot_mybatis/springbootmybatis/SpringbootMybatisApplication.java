package com.zzc.test.springboot_mybatis.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication

@ServletComponentScan
// mapper 接口类扫描包配置
@MapperScan("com.zzc.test.springboot_mybatis.springbootmybatis.dao")
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}
}
