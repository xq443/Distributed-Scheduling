package cn.wolfcode.job;

import cn.wolfcode.domain.FileCustom;
import cn.wolfcode.mapper.FileCustomMapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wolfcode-lanxw
 */
@Component
public class FileDataflowJob implements DataflowJob<FileCustom> {
    @Autowired
    private FileCustomMapper fileCustomMapper;
    @Override
    public List<FileCustom> fetchData(ShardingContext shardingContext) {
        List<FileCustom> fileCustoms = fileCustomMapper.fetchData(2);
        System.out.println("抓取时间:"+new Date()+",个数"+fileCustoms.size());
        return fileCustoms;
    }
    @Override
    public void processData(ShardingContext shardingContext, List<FileCustom> data) {
        for(FileCustom fileCustom:data){
            backUpFile(fileCustom);
        }
    }
    private void backUpFile(FileCustom fileCustom){
        try {
            //模拟备份动作
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行文件备份====>"+fileCustom);
        fileCustomMapper.changeState(fileCustom.getId(),1);
    }
}
