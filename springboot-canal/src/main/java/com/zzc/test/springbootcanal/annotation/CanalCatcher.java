package com.zzc.test.springbootcanal.annotation;

import com.zzc.test.springbootcanal.enums.ConnectionModel;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CanalCatcher {


    /**
     * 格式如下
     * host:ip-example
     */
    String value() default "127.0.0.1:3306-example";

    /**
     * 本连接在无canal消息推送的情况下的休眠时间
     */
    String idleSleepTime() default "";

    /**
     * 订阅表达式
     */
    String pattern() default ".*\\..*";

    String username() default "";

    String password() default "";

    /**
     *  * 尝试拿batchSize条记录
     *
     */
    int batchSize() default 1024;

    /**
     * timeout 默认 -1,则不阻塞拿取记录
     * timeout = 0 阻塞直到拿取batchSize条记录事件
     * timeout >0 则阻塞直到timeout才返回
     */
    int timeout() default -1;

    ConnectionModel model() default ConnectionModel.SIMPLE;

}
