package com.spring.schedule.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 10:28
 * @Description:
 */
public enum QuartzJobType {
    GROUPAUTOAPPROVE(1, "test","test", "0 0/1 * * * ?", "测试定时任务");

    private int id;

    private String jobName;

    private String triggerName;

    private String cron;

    private String description;

    public static Map<String, QuartzJobType> jobName2QuartzJobType;

    public static Map<String,QuartzJobType> triggerName2QuartzJobType;

    static {
        jobName2QuartzJobType = new HashMap<String, QuartzJobType>();
        triggerName2QuartzJobType = new HashMap<String,QuartzJobType>();

        for (QuartzJobType quartzJobType : QuartzJobType.values()) {
            jobName2QuartzJobType.put(quartzJobType.getJobName(), quartzJobType);
            triggerName2QuartzJobType.put(quartzJobType.triggerName, quartzJobType);
        }

    }
    /**
     *
     * @param jobName
     * @return
     */
    public static QuartzJobType byJobName(String jobName){
        return jobName2QuartzJobType.get(jobName);
    }
    /**
     *
     * @param triggerName
     * @return
     */
    public static QuartzJobType byTriggerName(String triggerName){
        return triggerName2QuartzJobType.get(triggerName);
    }
    /**
     * @param id
     * @param jobName
     * @param description
     */
    private QuartzJobType(int id, String jobName, String triggerName ,String cron, String description) {
        this.id = id;
        this.jobName = jobName;
        this.triggerName=triggerName;
        this.cron = cron;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
