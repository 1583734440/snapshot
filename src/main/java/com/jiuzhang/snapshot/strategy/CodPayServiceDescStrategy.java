package com.jiuzhang.snapshot.strategy;

import com.jiuzhang.snapshot.constants.PayWay;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Date 2021/1/31 16:15
 * @Author FU
 */
@Component
public class CodPayServiceDescStrategy implements ServiceDescStrategy{
    @Override
    public boolean isSatisfied(Order order, GoodsServiceSnapshotConfig config) {
        return config.getKey().equals("codpay") && order.getPayWay() == PayWay.codpay;
    }
}
