package com.zzc.test.springboothelloworld.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhengzechao
 * @date 2018/6/23
 * Email ooczzoo@gmail.com
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class SAd {

    private String hhh;


    @Override
    public String toString() {
        return "SAd{" +
                "hhh='" + hhh + '\'' +
                '}';
    }
}
