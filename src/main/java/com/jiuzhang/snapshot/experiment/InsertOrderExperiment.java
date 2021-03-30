package com.jiuzhang.snapshot.experiment;

import com.alibaba.fastjson.JSON;
import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.constants.PayWay;
import com.jiuzhang.snapshot.entity.OrderDO;
import com.jiuzhang.snapshot.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2021/2/1 15:25
 * @Author FU
 */
@Slf4j
//@Component
public class InsertOrderExperiment implements IExperiment {

    @Resource
    OrderMapper orderMapper;

    @Override
    public void test() {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderNo("E202009010000001234000011");
        orderDO.setUserId(123245L);
        orderDO.setShopId(654321L);
        orderDO.setState(1);
        orderDO.setDeliveryType(DeliveryType.express.getCode());
        orderDO.setPrice(100L);
        orderDO.setPayWay(PayWay.wxpay.getCode());
        orderDO.setBookTime(1609556430L);
        Map map = new HashMap<>();
        map.put("expressFee", 10L);
        orderDO.setExtend(JSON.toJSONString(map));
        Integer insertId = orderMapper.insert(orderDO);
        log.info("Order InsertId: " + insertId);
    }

}
