package com.jiuzhang.snapshot.snapshot;

import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.entity.Order;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceSnapshotProcessorTest {
    GoodsServiceSnapshotProcessor goodsServiceSnapshotProcessor = new GoodsServiceSnapshotProcessor();

    @Test
    public void testGetDescForExpressNofee() {

        Order order = new Order();
        order.setDeliveryType(DeliveryType.express);
        order.setExpressFee(0L);

        GoodsServiceSnapshotConfig config = new GoodsServiceSnapshotConfig();
        config.setKey("express");
        config.setTitle("快递发货");
        config.setDesc("支持快递发货，本商品$info");

        String desc = goodsServiceSnapshotProcessor.getDesc(order, config);

        Assert.assertEquals(desc, "支持快递发货，本商品免运费");

    }

    @Test
    public void testGetDescForExpressHasfee() {

        Order order = new Order();
        order.setDeliveryType(DeliveryType.express);
        order.setExpressFee(10L);

        GoodsServiceSnapshotConfig config = new GoodsServiceSnapshotConfig();
        config.setKey("express");
        config.setTitle("快递发货");
        config.setDesc("支持快递发货，本商品$info");

        String desc = goodsServiceSnapshotProcessor.getDesc(order, config);

        Assert.assertEquals(desc, "支持快递发货，本商品运费10");  // 必须写成断言形式

        // System.out.println(desc);  不叫单测！

    }
}