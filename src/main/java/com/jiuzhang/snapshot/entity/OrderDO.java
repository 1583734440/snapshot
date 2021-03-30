package com.jiuzhang.snapshot.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 订单对象，与数据库交互
 * @Date 2021/2/1 13:17
 * @Author FU
 */
@Data
public class OrderDO {

    /** 数据库主键 */
    private Long id;

    /** 订单号 */
    private String orderNo;

    /** 下单时间 */
    private Long bookTime;

    /** 下单店铺 */
    private Long shopId;

    /** 下单用户 */
    private Long userId;

    /** 订单状态 */
    private Integer state;

    /** 配送方式 */
    private Integer deliveryType;

    /** 支付方式 */
    private Integer payWay;

    /** 订单金额 */
    private Long price;

    /** 订单扩展信息, JSON 串 */
    private String extend;

    private Date gmtCreate;

    private Date gmtModify;
}
