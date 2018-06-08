package com.zzc.test.springbootquartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
@Component
public class LogListener implements JobListener {


    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println(context.getJobDetail().getDescription()+"上一次执行"+context.getPreviousFireTime());
        final Object parameter = context.getJobDetail().getJobDataMap().get("parameter");
        System.out.println(context.getJobDetail().getJobClass()+"被执行"+parameter);
    }
}
