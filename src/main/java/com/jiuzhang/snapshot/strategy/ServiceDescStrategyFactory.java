package com.jiuzhang.snapshot.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description 服务快照逻辑的策略工厂
 * @Date 2021/1/31 16:33
 * @Author FU
 */
@Component
public class ServiceDescStrategyFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private List<ServiceDescStrategy> serviceDescStrategies = new ArrayList<>();

    // 对新增开放，对修改关闭
    @PostConstruct
    public void init() {
        //TODO 如何将 strategy bean 实例的加载自动化
        Map<String, ServiceDescStrategy> serviceDescStrategyMap = applicationContext.getBeansOfType(ServiceDescStrategy.class);
        serviceDescStrategyMap.forEach(
                (key, value) -> {
                    serviceDescStrategies.add(value);
                }
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public List<ServiceDescStrategy> getServiceDescStrategies() {
        return Collections.unmodifiableList(serviceDescStrategies);
    }

}
