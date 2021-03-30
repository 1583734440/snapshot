package com.jiuzhang.snapshot.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.constants.OrderState;
import com.jiuzhang.snapshot.constants.PayWay;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.jiuzhang.snapshot.constants.OrderConstants.*;

/**
 * @Description 订单类
 * @Date 2021/1/27 14:34
 * @Author FU
 */
@Data
public class Order {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 下单时间
     */
    private Long bookTime;

    /**
     * 下单店铺
     */
    private Long shopId;

    /**
     * 下单用户
     */
    private Long userId;

    /**
     * 订单状态
     */
    private OrderState state;

    /**
     * 配送方式
     */
    private DeliveryType deliveryType;

    /**
     * 支付方式
     */
    private PayWay payWay;

    /**
     * 订单金额
     */
    private Long price;

    /**
     * 是否担保交易
     */
    private Boolean isSecured;

    /**
     * 所在店铺是否有线下门店
     */
    private Boolean hasRetailStore;

    /**
     * 订单快递运费，分为单位
     */
    private Long expressFee;

    /**
     * 同城配送起送金额
     */
    private Long localDeliveryStartPrice;

    /**
     * 同城配送配送金额
     */
    private Long localDeliveryPrice;

    public static Order from(OrderDO orderDO) {
        Order order = new Order();
        order.setOrderNo(orderDO.getOrderNo());
        order.setShopId(orderDO.getShopId());
        order.setUserId(orderDO.getUserId());
        order.setBookTime(orderDO.getBookTime());
        order.setState(OrderState.get(orderDO.getState()));
        order.setDeliveryType(DeliveryType.get(orderDO.getDeliveryType()));
        order.setPayWay(PayWay.get(orderDO.getPayWay()));
        order.setPrice(orderDO.getPrice());
        JSONObject jsonObject = JSON.parseObject(orderDO.getExtend());
        order.setIsSecured(jsonObject.getBoolean(IS_SECURED_ORDER));
        order.setHasRetailStore(jsonObject.getBoolean(HAS_RETAIL_STORE));
        order.setExpressFee(jsonObject.getLong(EXPRESS_FEE));
        order.setLocalDeliveryStartPrice(jsonObject.getLong(LOCAL_DELIVERY_START_PRICE));
        order.setLocalDeliveryPrice(jsonObject.getLong(LOCAL_DELIVERY_PRICE));
        return order;
    }

    public OrderDO toOrderDO() {
        OrderDO orderDO = new OrderDO();
        // Bean copy util
        orderDO.setOrderNo(this.orderNo);
        orderDO.setBookTime(this.bookTime);
        orderDO.setShopId(this.shopId);
        orderDO.setUserId(this.userId);
        orderDO.setState(this.state.getState());
        orderDO.setDeliveryType(this.deliveryType.getCode());
        orderDO.setPayWay(this.payWay.getCode());
        orderDO.setPrice(this.price);
        Map map = new HashMap<>();
        map.put(IS_SECURED_ORDER, this.isSecured);
        map.put(HAS_RETAIL_STORE, this.hasRetailStore);
        map.put(EXPRESS_FEE, this.expressFee);
        map.put(LOCAL_DELIVERY_START_PRICE, this.localDeliveryStartPrice);
        map.put(LOCAL_DELIVERY_PRICE, this.localDeliveryPrice);
        orderDO.setExtend(JSON.toJSONString(map));
        return orderDO;
    }
}
