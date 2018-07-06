package com.zzc.test.springbootcanal.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 *
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */
@ConfigurationProperties(prefix = "canal")
public class CanalProperties {

    /**
     * 全局空闲休眠时间
     */
    private Long globalIdleSleepTime;


    /**
     * 最大线程数
     */
    private Integer maxThreadSize;

    /**
     * 核心线程数
     */
    private Integer coreThreadSize;


    /**
     * 线程最大空闲存时间
     */

    private Long keepAliveTime;

    public Long getGlobalIdleSleepTime() {
        return globalIdleSleepTime;
    }

    public void setGlobalIdleSleepTime(Long globalIdleSleepTime) {
        this.globalIdleSleepTime = globalIdleSleepTime;
    }

    public Integer getMaxThreadSize() {
        return maxThreadSize;
    }

    public void setMaxThreadSize(Integer maxThreadSize) {
        this.maxThreadSize = maxThreadSize;
    }

    public Integer getCoreThreadSize() {
        return coreThreadSize;
    }

    public void setCoreThreadSize(Integer coreThreadSize) {
        this.coreThreadSize = coreThreadSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }
}
