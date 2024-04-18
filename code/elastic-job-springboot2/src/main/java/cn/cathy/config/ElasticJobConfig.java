package cn.wolfcode.config;

import cn.wolfcode.job.FileCustomElasticJob;
import cn.wolfcode.job.FileDataflowJob;
import cn.wolfcode.job.MyElasticJob;
import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class ElasticJobConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyElasticJob myElasticJob;
    @Autowired
    private CoordinatorRegistryCenter registryCenter;
    private static LiteJobConfiguration createJobConfiguration(final Class<? extends ElasticJob> jobClass,
                                                               final String cron,
                                                               final int shardingTotalCount,
                                                               final String shardingItemParameters,
                                                               boolean dataflowType) {
        // 定义作业核心配置
        JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getSimpleName(), cron, shardingTotalCount);
        if(!StringUtils.isEmpty(shardingItemParameters)){
            jobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
        }
        JobTypeConfiguration jobConfig = null;
        if(dataflowType){
            jobConfig = new DataflowJobConfiguration(jobCoreConfigurationBuilder.build(),jobClass.getCanonicalName(),true);
        }else{
            // 定义SIMPLE类型配置
            jobConfig = new SimpleJobConfiguration(jobCoreConfigurationBuilder.build(), jobClass.getCanonicalName());
        }
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(jobConfig).overwrite(true).build();
        return simpleJobRootConfig;
    }
    /*@Bean(initMethod = "init")
    public SpringJobScheduler initSimpleElasticJob(){
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(myElasticJob,registryCenter,createJobConfiguration(MyElasticJob.class,"0/3 * * * * ?",1,null));
        return springJobScheduler;
    }*/
    @Bean(initMethod = "init")
    public SpringJobScheduler initFileCustomElasticJob(FileCustomElasticJob fileCustomElasticJob){
        //增加任务事件追踪配置
        JobEventConfiguration jobEventConfiguration = new JobEventRdbConfiguration(dataSource);
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(
                fileCustomElasticJob,
                registryCenter,
                createJobConfiguration(FileCustomElasticJob.class,"0 0/1 * * * ?",4,"0=text,1=image,2=radio,3=vedio",false),
                jobEventConfiguration);
        return springJobScheduler;
    }
    /*@Bean(initMethod = "init")
    public SpringJobScheduler iniDataflowElasticJob(FileDataflowJob fileDataflowJob){
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(
                fileDataflowJob,
                registryCenter,
                createJobConfiguration(FileDataflowJob.class,"0 0/1 * * * ?",1,null,true));
        return springJobScheduler;
    }*/
}
