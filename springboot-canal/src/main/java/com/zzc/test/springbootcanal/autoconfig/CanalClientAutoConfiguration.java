package com.zzc.test.springbootcanal.autoconfig;

import com.zzc.test.springbootcanal.core.CanalClientManager;
import com.zzc.test.springbootcanal.properties.CanalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */

@ConditionalOnProperty(
        prefix = "canal",
        name = "enable",
        havingValue = "true")
@Configuration
@EnableConfigurationProperties(value = CanalProperties.class)
public class CanalClientAutoConfiguration {

    @Autowired
    private CanalProperties canalProperties;


    @Bean
    public CanalClientManager canalClientManager(){
        return new CanalClientManager(this.canalProperties);
    }


}
