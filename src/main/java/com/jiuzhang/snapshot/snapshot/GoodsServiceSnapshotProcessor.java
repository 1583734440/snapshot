package com.jiuzhang.snapshot.snapshot;

import com.jiuzhang.snapshot.config.GoodsServiceTemplate;
import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.constants.PayWay;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshot;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import com.jiuzhang.snapshot.strategy.ServiceDescStrategy;
import com.jiuzhang.snapshot.strategy.ServiceDescStrategyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 服务快照实现
 * @Date 2021/1/27 15:03
 * @Author FU
 */
@Component
public class GoodsServiceSnapshotProcessor {

    @Resource
    private GoodsServiceTemplate goodsServiceTemplate;

    @Resource
    private ServiceDescStrategyFactory serviceDescStrategyFactory;

    /**
     * 返回服务快照 List
     * @param order 订单信息
     * @param keys 所有服务主键
     * @return 服务快照
     */
    public List<GoodsServiceSnapshot> get(Order order, List<String> keys) {
        List<GoodsServiceSnapshot> result = new ArrayList<>();
        for (String key : keys) {
            GoodsServiceSnapshot goodsServiceSnapshot = get(order, key);
            if (goodsServiceSnapshot != null) {
                result.add(goodsServiceSnapshot);
            }
        }
        return result;
    }

    /**
     * 获取商品服务
     * @param order 订单信息
     * @param key 主键
     * @return 一个商品服务
     */
    public GoodsServiceSnapshot get(Order order, String key) {

        // 根据 key 获取相应的 configs
        List<GoodsServiceSnapshotConfig> configs = goodsServiceTemplate.getBy(key);

        // 根据 下单时间 和 configs 获取所匹配的 config
        GoodsServiceSnapshotConfig config = filter(order.getBookTime(), configs);

        // 根据 order 和 config 生成 goodsServiceSnapshot
        if (config == null) {
            return null;
        }

        GoodsServiceSnapshot goodsServiceSnapshot = getSnapshot(order, config);
        return goodsServiceSnapshot;
    }

    /**
     * 在相同的服务中过滤出对应的服务配置
     * @param bookTime 订单时间
     * @param configs 同一种服务的集合
     * @return 对应的服务
     */
    private GoodsServiceSnapshotConfig filter(Long bookTime, List<GoodsServiceSnapshotConfig> configs) {
        // start <= bookTime < end
        for (GoodsServiceSnapshotConfig config : configs) {
            if (config.getStart() <= bookTime && config.getEnd() > bookTime) {
                return config;
            }
        }
        return null;
    }

    /**
     * 获取对应的服务
     * @param order 订单信息
     * @param config 服务配置
     * @return 对应的服务
     */
    private GoodsServiceSnapshot getSnapshot(Order order, GoodsServiceSnapshotConfig config) {
        String desc = getDesc(order, config);
        if (desc == null) {
            return null;
        }
        GoodsServiceSnapshot goodsServiceSnapshot = new GoodsServiceSnapshot();
        goodsServiceSnapshot.setKey(config.getKey());
        goodsServiceSnapshot.setTitle(config.getTitle());
        goodsServiceSnapshot.setDesc(desc);
        return goodsServiceSnapshot;
    }

    /**
     * 生成服务内容信息
     * @param order 订单信息
     * @param config 服务配置
     * @return 服务内容
     */
    public String getDesc(Order order, GoodsServiceSnapshotConfig config) {
        // 采用了工厂模式
        for (ServiceDescStrategy serviceDescStrategy : serviceDescStrategyFactory.getServiceDescStrategies()) {
            if (serviceDescStrategy.isSatisfied(order, config)) {
                return serviceDescStrategy.getDesc(order, config);
            }
        }
        return null;
    }



}
