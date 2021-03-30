package com.jiuzhang.snapshot.strategy;

import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Date 2021/1/31 16:28
 * @Author FU
 */
@Component
public class SelfetchServiceDescStrategy implements ServiceDescStrategy {
    @Override
    public boolean isSatisfied(Order order, GoodsServiceSnapshotConfig config) {
        return config.getKey().equals("selfetch") && order.getDeliveryType() == DeliveryType.selfetch;
    }
}
