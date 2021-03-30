package com.jiuzhang.snapshot.strategy;

import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;

/**
 * @Description 服务信息策略
 * @Date 2021/1/31 16:11
 * @Author FU
 */
public interface ServiceDescStrategy {

    /**
     * 判断 服务配置 key 是否匹配， 并且订单信息是否符合
     * @param order 订单信息
     * @param config 服务配置
     * @return 是否匹配成功
     */
    boolean isSatisfied(Order order, GoodsServiceSnapshotConfig config);

    /**
     * 获取服务信息
     * @param order 订单信息
     * @param config 服务配置
     * @return 服务信息
     */
    default String getDesc(Order order, GoodsServiceSnapshotConfig config) {
        return config.getDesc();
    }
}
