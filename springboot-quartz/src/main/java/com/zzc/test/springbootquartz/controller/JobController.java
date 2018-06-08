package com.zzc.test.springbootquartz.controller;

import com.zzc.test.springbootquartz.domain.ScheduleJob;
import com.zzc.test.springbootquartz.service.JobService;
import javafx.scene.input.DataFormat;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
@RestController
public class JobController {




    @Autowired
    private JobService jobService;
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addJob(@RequestBody ScheduleJob job){
        try {

            jobService.addJob(job);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/list")
    public void listJob(){

    }
}
