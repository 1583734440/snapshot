package com.jiuzhang.snapshot.strategy;

import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Date 2021/1/31 16:20
 * @Author FU
 */
@Component
public class ExpressServiceDescStrategy implements ServiceDescStrategy {
    @Override
    public boolean isSatisfied(Order order, GoodsServiceSnapshotConfig config) {
        return config.getKey().equals("express") && order.getDeliveryType() == DeliveryType.express;
    }

    @Override
    public String getDesc(Order order, GoodsServiceSnapshotConfig config) {
        if (order.getExpressFee() == 0) {
            return config.getDesc().replace("$info", "免运费");
        } else {
            return config.getDesc().replace("$info", "运费" + order.getExpressFee().toString());
        }
    }
}
