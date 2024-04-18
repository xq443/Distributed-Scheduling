package cn.cathy.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class MyElasticJob implements SimpleJob {
    public void execute(ShardingContext shardingContext) {
        int i = new Random().nextInt(10);
        System.out.println("随机码···>"+i);
        System.out.println("定时任务开始====>"+new Date());
    }
}
