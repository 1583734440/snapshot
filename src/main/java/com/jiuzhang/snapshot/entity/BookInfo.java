package com.jiuzhang.snapshot.entity;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/2/7 11:34
 * @Author FU
 */
@Data
public class BookInfo {
    /** 下单的订单信息 */
    private Order order;

    /** 下单的商品信息 */
    private Goods goods;
}
