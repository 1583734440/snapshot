package com.jiuzhang.snapshot.experiment;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 服务快照实验框架
 * @Date 2021/1/28 16:18
 * @Author FU
 */
@Component
public class GoodsServiceSnapshotExperiments implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        doExperiments();
    }

    private void doExperiments() {
        Map<String, IExperiment> experiments = applicationContext.getBeansOfType(IExperiment.class);
        experiments.forEach(
                (key, value) -> {
                    value.test();
                }
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
