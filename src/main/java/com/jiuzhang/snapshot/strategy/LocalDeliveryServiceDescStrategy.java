package com.jiuzhang.snapshot.strategy;

import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Date 2021/1/31 16:21
 * @Author FU
 */
@Component
public class LocalDeliveryServiceDescStrategy implements ServiceDescStrategy {
    @Override
    public boolean isSatisfied(Order order, GoodsServiceSnapshotConfig config) {
        return config.getKey().equals("localDelivery") && order.getDeliveryType() == DeliveryType.localDelivery;
    }

    @Override
    public String getDesc(Order order, GoodsServiceSnapshotConfig config) {
        return config.getDesc().replace("$startPrice", order.getLocalDeliveryStartPrice().toString())
                .replace("$deliveryPrice", order.getLocalDeliveryPrice().toString());
    }
}
