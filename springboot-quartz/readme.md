


```java

  trigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJob.getTriggerName(),scheduleJob.getTriggerGroup())
                    .withDescription(scheduleJob.getDescription())
                    .startAt(date)
                    .build();
```
持久化在mysql的任务date如果已经超过指定时间(startAt())在`        scheduler.scheduleJob(jobDetail, trigger);`
之后并不会马上运行，而是在下次启动服务器的时候再从数据库中加载并运行，运行之后删除任务。(前提没有超过endAt限制)



```分布式环境下
一个间隔执行的事件并不是由一个机器获取执行到这台机器宕机为止，中途有可能由其他机器执行，(其实就应该这样)不然的话可能会导致某台
机器全是间断时间短执行的任务，造成负载很高。

```