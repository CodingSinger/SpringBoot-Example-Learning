package com.zzc.test.springbootquartz.service;

import com.zzc.test.springbootquartz.domain.ScheduleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */

@Service
public class JobService {
    @Autowired
    private Scheduler scheduler;
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public void addJob(ScheduleJob scheduleJob) throws ClassNotFoundException, SchedulerException, ParseException {

        Trigger trigger = null;
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(scheduleJob.getClassName());
        JobDataMap map = new JobDataMap(scheduleJob.getParameters());
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                .withDescription(scheduleJob.getDescription())
                .setJobData(map)
                .build();
        ScheduleBuilder scheduleBuilder = null;
        if (!StringUtils.isEmpty(scheduleJob.getExecuteTimeString())) {
            Date date = dataFormat.parse(scheduleJob.getExecuteTimeString());
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJob.getTriggerName(),scheduleJob.getTriggerGroup())
                    .withDescription(scheduleJob.getDescription())
                    .startAt(date)
                    .build();

        }else{
            if (StringUtils.isEmpty(scheduleJob.getCronExpression())){
                return;
            }else{

                scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing();

                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(scheduleJob.getTriggerName(),scheduleJob.getTriggerGroup())
                        .withDescription(scheduleJob.getDescription())
                        .withSchedule(scheduleBuilder)
                        .startNow()
                        .build();
            }
        }
        scheduler.scheduleJob(jobDetail, trigger);

    }

}
