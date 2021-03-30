package com.jiuzhang.snapshot.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 商品对象,与数据库交互
 * @Date 2021/2/1 13:21
 * @Author FU
 */
@Data
public class GoodsDO {

    /** 数据库主键 */
    private Long id;

    /** 商品ID */
    private Long goodsId;

    /** 下单店铺 */
    private Long shopId;

    /** 商品价格 */
    private Long price;

    /** 商品标题 */
    private String title;

    /** 商品描述 */
    private String description;

    /** 订单号 */
    private String orderNo;

    /** 商品扩展信息 */
    private String extend;

    private Date gmtCreate;

    private Date gmtModify;
}
