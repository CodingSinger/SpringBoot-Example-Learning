package com.zzc.test.springbootquartz.domain;

import lombok.Data;
import java.util.Map;

import java.util.Date;

/**
 * @author zhengzechao
 * @date 2018/6/8
 * Email ooczzoo@gmail.com
 */
@Data
public class ScheduleJob {

    private static final Long serialVersionUID = 1435515995276255188L;

    private Long id;

    private String className;

    private String cronExpression;

    private String jobName;

    private String jobGroup;

    private String triggerName;

    private String triggerGroup;

    private Boolean pause;

    private Boolean enable;

    private String description;

    private Date createTime;

    private Date lastUpdateTime;

    private String executeTimeString;


    private Map<String,Object> parameters;
}
