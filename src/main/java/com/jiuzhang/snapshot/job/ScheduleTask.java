package com.jiuzhang.snapshot.job;

import com.jiuzhang.snapshot.experiment.GoodsServiceSnapshotExperiment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description 周期任务
 * @Date 2021/1/28 16:39
 * @Author FU
 */
@Component
public class ScheduleTask {
    private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

    @Resource
    private GoodsServiceSnapshotExperiment goodsServiceSnapshotExperiment;

    @PostConstruct
    public void init() {
        es.scheduleAtFixedRate(
                this::test, 3, 5, TimeUnit.SECONDS
        );
    }

    private void test() {
        goodsServiceSnapshotExperiment.test();
    }

}
