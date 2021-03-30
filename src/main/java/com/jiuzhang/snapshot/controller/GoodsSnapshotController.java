package com.jiuzhang.snapshot.controller;

import com.jiuzhang.snapshot.annotation.ApiAnnotation;
import com.jiuzhang.snapshot.constants.OrderState;
import com.jiuzhang.snapshot.entity.BookInfo;
import com.jiuzhang.snapshot.entity.Goods;
import com.jiuzhang.snapshot.entity.Order;
import com.jiuzhang.snapshot.query.GoodsSnapshotQuery;
import com.jiuzhang.snapshot.result.*;
import com.jiuzhang.snapshot.service.DeliveryService;
import com.jiuzhang.snapshot.service.GoodsSnapshotService;
import com.jiuzhang.snapshot.service.PriceService;
import com.jiuzhang.snapshot.service.ShopService;
import com.jiuzhang.snapshot.util.OrderNoGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/goodsnapshot")
public class GoodsSnapshotController {

    @Resource
    GoodsSnapshotService goodsSnapshotService;
    @Resource
    PriceService priceService;
    @Resource
    ShopService shopService;
    @Resource
    DeliveryService deliveryService;

    @RequestMapping("/in")
    public String sss(Map<String, Object> a, Map<String, Object> b, Map<String, Object> c) {
        a.put("a", "aaaaaaaaaaa");
        b.put("b", "bbbbbbbbbbb");
        c.put("c", "ccccccccccc");
        return "a";
    }

    @ApiAnnotation
    @ResponseBody
    @RequestMapping("/book")
    public PlainResult<BookResult> book(@RequestBody BookInfo bookInfo) {
        // 下单基本信息校验
        check(bookInfo);

        String orderNo = OrderNoGenerator.generateOrderNo(bookInfo.getOrder().getUserId());;
        complete(bookInfo, orderNo);

        Boolean isSaveSuccess = goodsSnapshotService.save(bookInfo);
        BookResult bookResult = new BookResult(orderNo, bookInfo.getGoods().getGoodsId());

        return isSaveSuccess ? PlainResult.buildSuccess(bookResult) : PlainResult.buildFailed(500, "下单失败");

    }

    @ApiAnnotation
    @RequestMapping("/detail")
    @ResponseBody
    public PlainResult<GoodsSnapshotResult> detail(@RequestBody GoodsSnapshotQuery goodsSnapshotQuery) {

        Assert.notNull(goodsSnapshotQuery.getGoodsId(), "商品ID不能为空");
        Assert.notNull(goodsSnapshotQuery.getOrderNo(), "订单号不能为空");

        GoodsSnapshotResult goodsSnapshotResult = goodsSnapshotService.detail(goodsSnapshotQuery);
        return PlainResult.buildSuccess(goodsSnapshotResult);

    }


    private void check(BookInfo bookInfo) {
        Order order = bookInfo.getOrder();
        Goods goods = bookInfo.getGoods();
        Assert.notNull(order, "订单信息不能为空");
        Assert.notNull(goods, "商品信息不能为空");
        Assert.notNull(order.getUserId(), "用户信息不能为空");
        Assert.notNull(order.getShopId(), "店铺信息不能为空");
        Assert.notNull(order.getDeliveryType(), "订单配送方式不能为空");
        Assert.notNull(order.getPayWay(), "订单支付方式不能为空");
        Assert.notNull(goods.getGoodsId(), "商品ID不能为空");
        Assert.notNull(goods.getShopId(), "店铺信息不能为空");
        Assert.notNull(goods.getTitle(), "商品标题不能为空");
        Assert.notNull(goods.getKeys(), "商品的服务信息不能为空");
        Assert.isTrue(order.getUserId() > 0, "用户ID必须大于 0");
        Assert.isTrue(order.getShopId() > 0, "店铺ID必须大于 0");
        Assert.isTrue(goods.getShopId() > 0, "店铺ID必须大于 0");
    }

    private void complete(BookInfo bookInfo, String orderNo) {
        Order order = bookInfo.getOrder();
        order.setOrderNo(orderNo);
        order.setBookTime(System.currentTimeMillis() / 1000);
        order.setState(OrderState.book);
        order.setPrice(priceService.calcPrice(bookInfo));

        ShopResult shopResult = shopService.getShopInfo(bookInfo.getOrder().getShopId());
        order.setIsSecured(shopResult.getIsSecured());
        order.setHasRetailStore(shopResult.getHasRetailStore());

        Integer deliveryType = bookInfo.getOrder().getDeliveryType().getCode();
        DeliveryResult deliveryResult = deliveryService.getDeliveryInfo(deliveryType);
        order.setExpressFee(deliveryResult.getExpressFee());
        order.setLocalDeliveryPrice(deliveryResult.getLocalDeliveryPrice());
        order.setLocalDeliveryStartPrice(deliveryResult.getLocalDeliveryStartPrice());

        bookInfo.getGoods().setOrderNo(orderNo);

    }

}
