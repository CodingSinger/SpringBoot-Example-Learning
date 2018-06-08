package com.zzc.test.springbootquartz;

import com.zzc.test.springbootquartz.domain.MyFirstJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
public class QuartzTest {
    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(MyFirstJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger  //在某个点开始执行
                    //时间间隔为40s执行一次
                    //永远执行下去
                    ;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR,10);
            System.out.println(calendar.getTime());

            Date date = calendar.getTime();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 ? * L\t")
                    .withMisfireHandlingInstructionDoNothing();
            trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(date) //在某个点开始执行  如果已经过期了 则会立即执行
                    .withSchedule(simpleSchedule()
                            //时间间隔为40s执行一次
                            .withIntervalInSeconds(5)
                            .withRepeatCount(0)) //重复一次
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.start();


        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
