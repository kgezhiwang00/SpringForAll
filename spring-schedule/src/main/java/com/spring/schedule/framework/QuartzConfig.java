package com.spring.schedule.framework;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 10:48
 * @Description:
 */
@Configuration
public class QuartzConfig {
    private static final String DATASOURCE_QZDS_DRIVER="org.quartz.dataSource.qzDS.driver";

    private static final String DATASOURCE_QZDS_URL="org.quartz.dataSource.qzDS.URL";

    private static final String DATASOURCE_QZDS_USER="org.quartz.dataSource.qzDS.user";

    private static final String DATASOURCE_QZDS_PASSWORD="org.quartz.dataSource.qzDS.password";

    private static final String JOBSTORE_DATASOURCE="org.quartz.jobStore.dataSource";

    private static final String JOBSTORE_TABLEPREFIX="org.quartz.jobStore.tablePrefix";

    private static final String JOBSTORE_CLUSTERCHECKININTERVAL="org.quartz.jobStore.clusterCheckinInterval";

    private static final String JOBSTORE_ISCLUSTERED="org.quartz.jobStore.isClustered";

    private static final String JOBSTORE_USEPROPERTIES="org.quartz.jobStore.useProperties";

    private static final String JOBSTORE_DRIVERDELEGATECLASS="org.quartz.jobStore.driverDelegateClass";

    private static final String JOBSTORE_CLASS="org.quartz.jobStore.class";

    private static final String THREADPOOL_THREADSINHERITCONTEXTCLASSLOADEROFINITIALIZINGTHREAD="org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread";

    private static final String THREADPOOL_THREADPRIORITY="org.quartz.threadPool.threadPriority";

    private static final String THREADPOOL_THREADCOUNT="org.quartz.threadPool.threadCount";

    private static final String THREADPOOL_CLASS="org.quartz.threadPool.class";

    private static final String SCHEDULER_INSTANCENAME="org.quartz.scheduler.instanceName";

    private static final String SCHEDULER_INSTANCEID="org.quartz.scheduler.instanceId";

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    private Environment environment;

    /**
     * 调度工厂bean
     *
     * @return
     * @author Roger
     * @throws Exception
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean[] cronJobTrigger)
            throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);

        // QuartzScheduler 延时启动，应用启动完20秒后 QuartzScheduler 再启动
        factory.setStartupDelay(0);

        // 用于quartz集群,加载quartz数据源配置
        factory.setQuartzProperties(quartzProperties());
        // 注册触发器
        Trigger[] triggers = new Trigger[cronJobTrigger.length];
        for (int i = 0; i < triggers.length; i ++) {
            triggers[i] = cronJobTrigger[i].getObject();
        }
        factory.setTriggers(triggers);
        factory.setJobFactory(myJobFactory);
        return factory;
    }

    /**
     * 加载quartz数据源配置
     *
     * @return
     * @author Roger
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        Properties properties  = new Properties();
        properties.setProperty(DATASOURCE_QZDS_DRIVER, environment.getProperty(DATASOURCE_QZDS_DRIVER));
        properties.setProperty(DATASOURCE_QZDS_URL, environment.getProperty(DATASOURCE_QZDS_URL));
        properties.setProperty(DATASOURCE_QZDS_USER, environment.getProperty(DATASOURCE_QZDS_USER));
        properties.setProperty(DATASOURCE_QZDS_PASSWORD, environment.getProperty(DATASOURCE_QZDS_PASSWORD));
        properties.setProperty(JOBSTORE_DATASOURCE, environment.getProperty(JOBSTORE_DATASOURCE));
        properties.setProperty(JOBSTORE_TABLEPREFIX, environment.getProperty(JOBSTORE_TABLEPREFIX));
        properties.setProperty(JOBSTORE_CLUSTERCHECKININTERVAL, environment.getProperty(JOBSTORE_CLUSTERCHECKININTERVAL));
        properties.setProperty(JOBSTORE_ISCLUSTERED, environment.getProperty(JOBSTORE_ISCLUSTERED));
        properties.setProperty(JOBSTORE_USEPROPERTIES, environment.getProperty(JOBSTORE_USEPROPERTIES));
        properties.setProperty(JOBSTORE_DRIVERDELEGATECLASS, environment.getProperty(JOBSTORE_DRIVERDELEGATECLASS));
        properties.setProperty(JOBSTORE_CLASS, environment.getProperty(JOBSTORE_CLASS));
        properties.setProperty(THREADPOOL_THREADSINHERITCONTEXTCLASSLOADEROFINITIALIZINGTHREAD, environment.getProperty(THREADPOOL_THREADSINHERITCONTEXTCLASSLOADEROFINITIALIZINGTHREAD));
        properties.setProperty(THREADPOOL_THREADPRIORITY, environment.getProperty(THREADPOOL_THREADPRIORITY));
        properties.setProperty(THREADPOOL_THREADCOUNT, environment.getProperty(THREADPOOL_THREADCOUNT));
        properties.setProperty(THREADPOOL_CLASS, environment.getProperty(THREADPOOL_CLASS));
        properties.setProperty(SCHEDULER_INSTANCENAME, environment.getProperty(SCHEDULER_INSTANCENAME));
        properties.setProperty(SCHEDULER_INSTANCEID, environment.getProperty(SCHEDULER_INSTANCEID));
        return properties;
    }
}
