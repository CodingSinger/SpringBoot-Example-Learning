package com.zzc.test.springbootquartz;

import com.zzc.test.springbootquartz.properties.QuartzProperties;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;


/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
public class QuartzAutoConfigutation {
    @Autowired
    private QuartzProperties quartzProperties;

    @Autowired
    private JobListener listener;
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setSchedulerName("TASK_EXECUTOR");
        // 延迟10秒启动
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setGlobalJobListeners();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return schedulerFactoryBean;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws Exception {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.getListenerManager().addJobListener(listener);
        scheduler.start();
        return scheduler;
    }
}
