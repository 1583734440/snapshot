package com.jiuzhang.snapshot.experiment;

import com.alibaba.fastjson.JSON;
import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.constants.PayWay;
import com.jiuzhang.snapshot.controller.GoodsSnapshotController;
import com.jiuzhang.snapshot.entity.BookInfo;
import com.jiuzhang.snapshot.entity.Goods;
import com.jiuzhang.snapshot.entity.Order;
import com.jiuzhang.snapshot.query.GoodsSnapshotQuery;
import com.jiuzhang.snapshot.result.BookResult;
import com.jiuzhang.snapshot.result.GoodsSnapshotResult;
import com.jiuzhang.snapshot.result.PlainResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Description TODO
 * @Date 2021/2/28 11:53
 * @Author FU
 */
@Slf4j
@Component
public class BookExperiment implements IExperiment {

    @Resource
    private GoodsSnapshotController goodsSnapshotController;

    Random random = new Random(System.currentTimeMillis());

    @Override
    public void test() {
        BookInfo bookInfo = new BookInfo();
        Order order = new Order();

        Long shopId = 654321L + random.nextInt(10000);
        Long userId = 1234L + random.nextInt(1000);
        Long goodsId = 5678L + random.nextInt(4000);
        order.setShopId(shopId);
        order.setUserId(userId);
        order.setDeliveryType(DeliveryType.express);
        order.setPayWay(PayWay.codpay);
        bookInfo.setOrder(order);

        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setShopId(shopId);
        goods.setPrice(200L);
        goods.setTitle("认养一头牛");
        goods.setDesc("2箱*250g");
        goods.setKeys("express,codpay,secureService");
        bookInfo.setGoods(goods);

        PlainResult<BookResult> bookResult = goodsSnapshotController.book(bookInfo);
        GoodsSnapshotQuery goodsSnapshotQuery = new GoodsSnapshotQuery();
        goodsSnapshotQuery.setOrderNo(bookResult.getData().getOrderNo());
        goodsSnapshotQuery.setGoodsId(bookResult.getData().getGoodsId());
        PlainResult<GoodsSnapshotResult> detail = goodsSnapshotController.detail(goodsSnapshotQuery);
        log.info("下单结果:" + JSON.toJSONString(bookResult));
        log.info("快照信息:" + JSON.toJSONString(detail));
    }
}
