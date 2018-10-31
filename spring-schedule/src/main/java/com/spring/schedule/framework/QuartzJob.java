package com.spring.schedule.framework;


import org.quartz.JobExecutionContext;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 10:33
 * @Description:
 */
public interface QuartzJob {

    void service(JobExecutionContext context);
}
