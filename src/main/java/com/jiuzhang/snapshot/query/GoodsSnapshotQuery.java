package com.jiuzhang.snapshot.query;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/3/1 10:47
 * @Author FU
 */
@Data
public class GoodsSnapshotQuery {

    /** 订单号 */
    private String orderNo;

    /** 商品ID */
    private Long goodsId;
}
