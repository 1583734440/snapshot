package com.jiuzhang.snapshot.experiment;

import com.alibaba.fastjson.JSON;
import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.constants.PayWay;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshot;
import com.jiuzhang.snapshot.entity.Order;
import com.jiuzhang.snapshot.snapshot.GoodsServiceSnapshotProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 测试 动态获取 服务快照
 * @Date 2021/1/28 16:21
 * @Author FU
 */
@Slf4j
@Component
public class GoodsServiceSnapshotExperiment implements IExperiment{

    @Resource
    GoodsServiceSnapshotProcessor goodsServiceSnapshotProcessor;

    @Override
    public void test() {
        testForExpressNoFee();
        testForExpressHasFee();
        testForLocalDelivery();
    }


    public void testForExpressNoFee() {

        Order order = new Order();
        order.setExpressFee(0L);
        order.setBookTime(1609556430L);
        order.setDeliveryType(DeliveryType.express);
        order.setPayWay(PayWay.codpay);

        List<String> keys = Arrays.asList("express", "codpay");

        List<GoodsServiceSnapshot> goodsServiceSnapshots = goodsServiceSnapshotProcessor.get(order, keys);
        log.info("goodsServiceSnapshots: " + JSON.toJSONString(goodsServiceSnapshots));
    }

    public void testForExpressHasFee() {

        Order order = new Order();
        order.setExpressFee(10L);
        order.setBookTime(1609556430L);
        order.setDeliveryType(DeliveryType.express);
        order.setPayWay(PayWay.codpay);

        List<String> keys = Arrays.asList("express", "codpay");

        List<GoodsServiceSnapshot> goodsServiceSnapshots = goodsServiceSnapshotProcessor.get(order, keys);
        log.info("goodsServiceSnapshots: " + JSON.toJSONString(goodsServiceSnapshots));
    }

    public void testForLocalDelivery() {

        Order order = new Order();
        order.setExpressFee(0L);
        order.setBookTime(1609556430L);
        order.setDeliveryType(DeliveryType.localDelivery);
        order.setLocalDeliveryStartPrice(1L);
        order.setLocalDeliveryPrice(10L);
        order.setPayWay(PayWay.codpay);
        order.setIsSecured(true);

        List<String> keys = Arrays.asList("localDelivery", "codpay", "secureService");

        List<GoodsServiceSnapshot> goodsServiceSnapshots = goodsServiceSnapshotProcessor.get(order, keys);
        log.info("goodsServiceSnapshots: " + JSON.toJSONString(goodsServiceSnapshots));
    }
}
