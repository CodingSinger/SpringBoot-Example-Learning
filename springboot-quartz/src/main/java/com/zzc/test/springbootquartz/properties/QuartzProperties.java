package com.zzc.test.springbootquartz.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
@Data
@ConfigurationProperties(prefix = "quartz")
public class QuartzProperties {
    @Value("name")
    private String name;


    public QuartzProperties() {
    }
}
