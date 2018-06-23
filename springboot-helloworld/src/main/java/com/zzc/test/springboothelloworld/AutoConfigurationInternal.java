package com.zzc.test.springboothelloworld;

import com.zzc.test.springboothelloworld.domain.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengzechao
 * @date 2018/6/22
 * Email ooczzoo@gmail.com
 */
@ConditionalOnProperty(
        prefix = "custom",
        name={"name"},
        havingValue = "hello")

@Configuration
public class AutoConfigurationInternal {
    public AutoConfigurationInternal() {
    }

    @Bean
    public String hello(){
        System.out.println("hello");
        return "hello";
    }
}
